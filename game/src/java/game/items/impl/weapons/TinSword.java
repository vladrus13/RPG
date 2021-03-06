package game.items.impl.weapons;

import ru.vladrus13.rpg.world.items.Item;
import ru.vladrus13.rpg.world.items.Weapon;

/**
 * @author vladrus13 on 26.03.2021
 **/
public class TinSword extends Weapon {

    public static final int staticId = 1;

    public TinSword() {
        super(staticId);
    }

    public static TinSword getInstance() {
        return new TinSword();
    }

    public static int getId() {
        return staticId;
    }

    @Override
    public Item copy() {
        return getInstance();
    }
}
