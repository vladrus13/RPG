package game.items.impl.money;

import ru.vladrus13.rpg.world.items.Item;
import ru.vladrus13.rpg.world.items.Money;

public class Coins extends Money {

    public static int staticId = 101;

    public Coins() {
        super(staticId);
    }

    public static Coins getInstance() {
        return new Coins();
    }

    @Override
    public Item copy() {
        return getInstance();
    }

    public static int getId() {
        return staticId;
    }
}
