package ru.vladrus13.rpg.saves;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Annotation;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SaveHolder {
    public static Save save;
    private static final Logger logger = Logger.getLogger(SaveHolder.class.getName());

    public static Save getSave() {
        return save;
    }

    public static void newSave() {
        save = new Save();
    }

    public static void setSave(Save save) {
        SaveHolder.save = save;
    }

    public static Variables getVariables(Region region) {
        return save.regionsData.get(region.name);
    }

    public static String get(Region region, String key) {
        Variables variables = getVariables(region);
        if (variables == null) {
            return null;
        }
        return variables.variables.get(key);
    }

    public Long getLong(Region region, String key) {
        String ret = get(region, key);
        if (ret == null) {
            return null;
        }
        return Long.parseLong(ret);
    }

    public Integer getInt(Region region, String key) {
        String ret = get(region, key);
        if (ret == null) {
            return null;
        }
        return Integer.parseInt(ret);
    }

    public static void setVariable(Region region, String key, String value) {
        Variables variables = getVariables(region);
        if (variables == null) {
            save.regionsData.put(region.name, new Variables());
        }
        variables = getVariables(region);
        variables.variables.put(key, value);
    }

    public static void setHero(Actor hero) {
        save.hero = hero;
    }

    private static boolean isSimple(Class<?> type) {
        return type.isAssignableFrom(Long.class) ||
                type.isAssignableFrom(String.class) ||
                type.isAssignableFrom(Integer.class) ||
                type.isAssignableFrom(long.class) ||
                type.isAssignableFrom(int.class);
    }

    private static Object getSimple(Object object, Class<?> type) {
        if (!isSimple(type)) {
            throw new IllegalStateException("Can't get simple from: " + type.getName());
        }
        return object;
    }

    public static JSONObject recursiveJSON(Object object) throws IllegalAccessException {
        Class<?> eClass = object.getClass();
        JSONObject jsonObject = new JSONObject();
        while (!eClass.isInstance(Object.class)) {
            for (Field field : eClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (Arrays.stream(field.getAnnotations()).anyMatch(annotation -> annotation instanceof SaveConstante)) {
                    SaveConstante annotation = field.getAnnotation(SaveConstante.class);
                    String name = annotation.name();
                    if (Arrays.stream(field.get(object).getClass().getAnnotations()).anyMatch(it -> it instanceof Savable)) {
                        jsonObject.put(name, recursiveJSON(field.get(object)));
                    } else {
                        if (isSimple(field.getType())) {
                            jsonObject.put(name, field.get(object));
                            continue;
                        }
                        if (field.getType().isAssignableFrom(Map.class)) {
                            JSONObject map = new JSONObject();
                            ParameterizedType generic = (ParameterizedType) field.getGenericType();
                            Class<?> first = (Class<?>) generic.getActualTypeArguments()[0];
                            Class<?> second = (Class<?>) generic.getActualTypeArguments()[1];
                            if (!first.isAssignableFrom(String.class)) {
                                throw new IllegalStateException("first argument (" + first.getName() + ") of map is not a String class");
                            }
                            @SuppressWarnings("unchecked")
                            Map<String, ?> casted = (Map<String, ?>) field.get(object);
                            if (Arrays.stream(second.getDeclaredAnnotations()).anyMatch(it -> it instanceof Savable)) {
                                for (Map.Entry<String, ?> iterable : casted.entrySet()) {
                                    map.put(iterable.getKey(), recursiveJSON(iterable.getValue()));
                                }
                            } else {
                                if (isSimple(second)) {
                                    for (Map.Entry<String, ?> iterable : casted.entrySet()) {
                                        map.put(iterable.getKey(), iterable.getValue());
                                    }
                                } else {
                                    throw new IllegalStateException("Can't save type: " + field.getType().getName());
                                }
                            }
                            jsonObject.put(name, map);
                            continue;
                        }
                        if (field.getType().isAssignableFrom(ArrayList.class)) {
                            JSONArray array = new JSONArray();
                            ParameterizedType generic = (ParameterizedType) field.getGenericType();
                            Class<?> first = (Class<?>) generic.getActualTypeArguments()[0];
                            if (Arrays.stream(first.getDeclaredAnnotations()).anyMatch(it -> it instanceof Savable)) {
                                ArrayList<?> arrayList = (ArrayList<?>) field.get(object);
                                for (Object iterable : arrayList) {
                                    array.put(recursiveJSON(iterable));
                                }
                            } else {
                                if (isSimple(first)) {
                                    ArrayList<?> arrayList = (ArrayList<?>) field.get(object);
                                    for (Object iterable : arrayList) {
                                        array.put(iterable);
                                    }
                                } else {
                                    throw new IllegalStateException("Can't save type: " + field.getType().getName());
                                }
                            }
                            jsonObject.put(name, array);
                            continue;
                        }
                        if (field.getType().isAssignableFrom(Direction.class)) {
                            jsonObject.put(name, ((Direction) field.get(object)).getCapitalize());
                            continue;
                        }
                        throw new IllegalStateException("Can't save type: " + field.getType().getName());
                    }
                }
            }
            eClass = eClass.getSuperclass();
        }
        return jsonObject;
    }

    public static void save(Path path) {
        try (BufferedWriter bf = Files.newBufferedWriter(path)) {
            bf.write(recursiveJSON(save).toString(4));
        } catch (IOException | IllegalAccessException e) {
            Writer.printStackTrace(logger, e);
        }
    }

    public static Object recursiveSet(JSONObject object, Class<?> clazz) {
        if (Arrays.stream(clazz.getAnnotations()).anyMatch(it -> it instanceof Savable)) {
            Savable savable = clazz.getAnnotation(Savable.class);
            Object newInstance = null;
            if (savable.implemented()) {
                try {
                    Constructor<?> constructor = clazz.getConstructor(JSONObject.class);
                    constructor.setAccessible(true);
                    newInstance = constructor.newInstance(object);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    Writer.printStackTrace(logger, e);
                }
            } else {
                Map<Integer, Object> constructorFields = new HashMap<>();
                for (Field field : clazz.getDeclaredFields()) {
                    if (Arrays.stream(field.getAnnotations()).anyMatch(it -> it instanceof SaveConstante)) {
                        SaveConstante saveConstante = field.getAnnotation(SaveConstante.class);
                        if (saveConstante.constructor() == -1) continue;
                        Object part = recursiveSet(object.getJSONObject(saveConstante.name()), field.getType());
                        constructorFields.put(saveConstante.constructor(), part);
                    }
                }
                Object[] constructors = constructorFields
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparingInt(Map.Entry::getKey))
                        .toArray();
                Class<?>[] classesConstructors = new Class<?>[constructors.length];
                classesConstructors = Arrays.stream(constructors)
                        .map(Object::getClass)
                        .collect(Collectors.toCollection(ArrayList::new))
                        .toArray(classesConstructors);
                try {
                    Constructor<?> constructor = clazz.getConstructor(classesConstructors);
                    newInstance = constructor.newInstance(constructors);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    Writer.printStackTrace(logger, e);
                }
            }
            if (newInstance == null) {
                return null;
            }
            for (Field field : clazz.getDeclaredFields()) {
                if (Arrays.stream(field.getAnnotations()).anyMatch(it -> it instanceof SaveConstante)) {
                    SaveConstante saveConstante = field.getAnnotation(SaveConstante.class);
                    if (saveConstante.constructor() != -1) continue;
                    if (saveConstante.setNameFunction().isEmpty()) {
                        try {
                            field.set(newInstance, recursiveSet(object.getJSONObject(saveConstante.name()), field.getType()));
                        } catch (IllegalAccessException e) {
                            Writer.printStackTrace(logger, e);
                        }
                    } else {
                        try {
                            clazz.getMethod(saveConstante.setNameFunction(), JSONObject.class).invoke(newInstance, object.getJSONObject(saveConstante.name()));
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            Writer.printStackTrace(logger, e);
                        }
                    }
                }
            }
            return newInstance;
        } else {
            if (isSimple(clazz)) {
                return getSimple(object, clazz);
            }

            throw new IllegalStateException("Can't read type: " + clazz.getName());
        }
    }

    public static void load(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String json = br.lines().collect(Collectors.joining("\n"));
            save = (Save) recursiveSet(new JSONObject(new JSONTokener(json)), Save.class);
        } catch (IOException e) {
            Writer.printStackTrace(logger, e);
        }
    }
}
