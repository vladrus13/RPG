package ru.vladrus13.rpg.saves;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import ru.vladrus13.jgraphic.bean.Point;
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
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SaveHolder {
    private static final Logger logger = Logger.getLogger(SaveHolder.class.getName());
    public static Save save;

    public static Save getSave() {
        return save;
    }

    public static void setSave(Save save) {
        SaveHolder.save = save;
    }

    public static void newSave() {
        save = new Save();
    }

    private static boolean isSimple(Class<?> type) {
        return type.isAssignableFrom(Long.class) ||
                type.isAssignableFrom(String.class) ||
                type.isAssignableFrom(Integer.class) ||
                type.isAssignableFrom(long.class) ||
                type.isAssignableFrom(int.class);
    }

    private static JSONObject getSimpleJSON(Object object, Class<?> type) {
        if (!isSimple(type)) {
            throw new IllegalStateException("Can't get simple from: " + type.getName());
        }
        return new JSONObject(object);
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
        if (eClass.isAssignableFrom(Point.class)) {
            jsonObject.put("x", ((Point) object).x);
            jsonObject.put("y", ((Point) object).y);
            return jsonObject;
        }
        if (isSimple(eClass)) {
            return getSimpleJSON(object, eClass);
        }
        if (Arrays.stream(eClass.getMethods()).anyMatch(method -> method.getName().equals("getPrivateFields"))) {
            try {
                Method method = eClass.getMethod("getPrivateFields");
                @SuppressWarnings("unchecked")
                Map<String, Object> result = (Map<String, Object>) method.invoke(object);
                for (Map.Entry<String, Object> entry : result.entrySet()) {
                    jsonObject.put(entry.getKey(), recursiveJSON(entry.getValue()));
                }
            } catch (NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
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
                            jsonObject.put(name, ((Direction) field.get(object)).toString());
                            continue;
                        }
                        if (field.getType().isAssignableFrom(Point.class)) {
                            JSONObject point = new JSONObject();
                            Point real = (Point) field.get(object);
                            point.put("x", real.x);
                            point.put("y", real.y);
                            jsonObject.put(name, real);
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

    public static Object recursiveSet(Object object, Class<?> clazz) {
        if (Arrays.stream(clazz.getAnnotations()).anyMatch(it -> it instanceof Savable)) {
            if (!(object instanceof JSONObject)) {
                throw new IllegalArgumentException("Readen object can't be not JSONObject, if current class is Savable");
            }
            JSONObject jsonObject = (JSONObject) object;
            Savable savable = clazz.getAnnotation(Savable.class);
            Object newInstance = null;
            if (savable.implemented()) {
                try {
                    Method method = clazz.getMethod("getInstance", Object.class);
                    method.setAccessible(true);
                    newInstance = method.invoke(clazz, object);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    Writer.printStackTrace(logger, e);
                }
            } else {
                Map<Integer, Object> constructorFields = new HashMap<>();
                Map<Integer, Class<?>> constructorTypes = new HashMap<>();
                for (Field field : clazz.getDeclaredFields()) {
                    if (Arrays.stream(field.getAnnotations()).anyMatch(it -> it instanceof SaveConstante)) {
                        SaveConstante saveConstante = field.getAnnotation(SaveConstante.class);
                        if (saveConstante.constructor() == -1) continue;
                        Object part = recursiveSet(jsonObject.get(saveConstante.name()), field.getType());
                        constructorFields.put(saveConstante.constructor(), part);
                        constructorTypes.put(saveConstante.constructor(), field.getType());
                    }
                }
                Object[] constructors = constructorFields
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparingInt(Map.Entry::getKey))
                        .map(Map.Entry::getValue)
                        .toArray();
                Class<?>[] classesConstructors = new Class<?>[constructors.length];
                classesConstructors = constructorTypes
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparingInt(Map.Entry::getKey))
                        .map(Map.Entry::getValue)
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
                    field.setAccessible(true);
                    if (saveConstante.setNameFunction().isEmpty()) {
                        try {
                            field.set(newInstance, recursiveSet(jsonObject.get(saveConstante.name()), field.getType()));
                        } catch (IllegalAccessException e) {
                            Writer.printStackTrace(logger, e);
                        }
                    } else {
                        try {
                            clazz.getMethod(saveConstante.setNameFunction(), JSONObject.class).invoke(newInstance, jsonObject.getJSONObject(saveConstante.name()));
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
            if (clazz.isAssignableFrom(Point.class)) {
                if (!(object instanceof JSONObject)) {
                    throw new IllegalArgumentException("Readen object can't be not JSONObject, if current class is Point");
                }
                JSONObject jsonObject = (JSONObject) object;
                return new Point(jsonObject.getLong("x"), jsonObject.getLong("y"));
            }
            if (clazz.isAssignableFrom(Direction.class)) {
                if (!(object instanceof String)) {
                    throw new IllegalArgumentException("Readen object can't be not String, if current class is Direction");
                }
                String str = (String) object;
                return Direction.valueOf(str);
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

    public static void loadQuickSave() {
        load(Path.of("../resources/saves/quicksave.save"));
    }

    public static void quickSave() {
        save(Path.of("../resources/saves/quicksave.save"));
    }
}
