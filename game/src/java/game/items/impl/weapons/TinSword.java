package game.items.impl.weapons;

import ru.vladrus13.rpg.world.items.Item;
import ru.vladrus13.rpg.world.items.Weapon;

/**
 * @author vladrus13 on 26.03.2021
 **/
public class TinSword extends Weapon {

    public static int staticId = 1;

    public TinSword() {
        super(staticId);
    }

    public static TinSword getInstance() {
        return new TinSword();
    }

    @Override
    public Item copy() {
        return getInstance();
    }

    public static int getId() {
        return staticId;
    }
}
