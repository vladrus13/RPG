package ru.vladrus13.game.items;

import ru.vladrus13.game.items.impl.weapons.Sword;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.world.items.Item;
import ru.vladrus13.rpg.world.items.Money;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @author vladrus13 on 24.03.2021
 **/
public class ItemFactory {

    private final static Logger logger = Logger.getLogger(ItemFactory.class.getName());

    public final static ResourceBundle resourceBundle = ResourceBundle.getBundle("internationalization.items", MainProperty.getLocale());

    private final static int MAX_ID = 1000;

    private final static Class<?>[] items = new Class<?>[MAX_ID];

    static {
        items[1] = Sword.class;
        items[101] = Money.class;
    }

    public static Item get(int id) throws GameException {
        try {
            return (Item) items[id].getMethod("getInstance").invoke(items[id]);
        } catch (NoSuchMethodException e) {
            throw new GameException("No item with id: " + id);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new GameException(e);
        }
    }

    public static String getBundle(String s) {
        return resourceBundle.getString(s);
    }
}
