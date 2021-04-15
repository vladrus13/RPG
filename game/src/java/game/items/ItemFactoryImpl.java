package game.items;

import game.items.impl.money.Coins;
import game.items.impl.weapons.TinSword;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.rpg.world.factory.ItemFactory;
import ru.vladrus13.rpg.world.items.Item;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class ItemFactoryImpl {

    private final static int MAX_ID = 1000;

    private final static Class<?>[] items = new Class<?>[MAX_ID];

    static {
        items[1] = TinSword.class;
        items[101] = Coins.class;
        ItemFactory.items = items;
    }
}
