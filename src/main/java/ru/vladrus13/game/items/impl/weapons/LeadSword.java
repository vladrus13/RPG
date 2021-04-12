package ru.vladrus13.game.items.impl.weapons;

import ru.vladrus13.rpg.world.items.Weapon;

/**
 * @author vladrus13 on 26.03.2021
 **/
public class LeadSword extends Weapon {

    public static int staticId = 2;

    public LeadSword() {
        super(staticId);
    }

    public static LeadSword getInstance() {
        return new LeadSword();
    }

    public static int getId() {
        return staticId;
    }
}
