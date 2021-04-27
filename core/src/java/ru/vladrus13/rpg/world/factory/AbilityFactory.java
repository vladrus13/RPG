package ru.vladrus13.rpg.world.factory;

import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.rpg.world.actors.Ability;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class AbilityFactory {

    private final static Logger logger = Logger.getLogger(ItemFactory.class.getName());

    public static Class<?>[] abilities;

    public static Ability get(int id) throws AppException {
        try {
            return (Ability) abilities[id].getMethod("getInstance").invoke(abilities[id]);
        } catch (NoSuchMethodException e) {
            throw new AppException("No ability with id: " + id);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e);
        }
    }
}
