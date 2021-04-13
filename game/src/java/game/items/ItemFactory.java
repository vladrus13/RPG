package game.items;

import game.items.impl.money.Coins;
import game.items.impl.weapons.TinSword;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.rpg.world.items.Item;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

/**
 * @author vladrus13 on 24.03.2021
 **/
public class ItemFactory {

    private final static Logger logger = Logger.getLogger(ItemFactory.class.getName());

    private final static int MAX_ID = 1000;

    private final static Class<?>[] items = new Class<?>[MAX_ID];

    static {
        items[1] = TinSword.class;
        items[101] = Coins.class;
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
}
