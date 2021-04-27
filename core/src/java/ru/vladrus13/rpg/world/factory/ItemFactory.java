package ru.vladrus13.rpg.world.factory;

import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.rpg.world.items.Item;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class ItemFactory {

    private final static Logger logger = Logger.getLogger(ItemFactory.class.getName());

    public static Class<?>[] items;

    public static Item get(int id) throws AppException {
        try {
            return (Item) items[id].getMethod("getInstance").invoke(items[id]);
        } catch (NoSuchMethodException e) {
            throw new AppException("No item with id: " + id);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e);
        }
    }
}
