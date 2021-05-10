package game.items;

import game.items.impl.money.Coins;
import game.items.impl.weapons.TinSword;

import static ru.vladrus13.rpg.world.factory.ItemFactory.items;

public class ItemFactoryImpl {

    private final static int MAX_ID = 1000;

    public static void init() {
        items = new Class<?>[MAX_ID];
        items[1] = TinSword.class;
        items[101] = Coins.class;
    }
}
