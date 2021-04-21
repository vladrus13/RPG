package game.abilities;

import game.abilities.impl.Heal;
import game.abilities.impl.Splash;
import game.items.impl.money.Coins;
import game.items.impl.weapons.TinSword;
import ru.vladrus13.rpg.world.factory.AbilityFactory;
import ru.vladrus13.rpg.world.factory.ItemFactory;

public class AbilityFactoryImpl {
    private final static int MAX_ID = 1000;

    private final static Class<?>[] abilities = new Class<?>[MAX_ID];

    public static void init() {
        abilities[1] = Splash.class;
        abilities[2] = Heal.class;
        AbilityFactory.abilities = abilities;
    }
}
