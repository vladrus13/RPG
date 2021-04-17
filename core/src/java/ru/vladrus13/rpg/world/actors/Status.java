package ru.vladrus13.rpg.world.actors;

import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.saves.SaveConstante;

@Savable(implemented = false)
public class Status {
    @SaveConstante(name = "hp", constructor = 0)
    public int hp;
    @SaveConstante(name = "mp", constructor = 1)
    public int mp;
    @SaveConstante(name = "attack", constructor = 2)
    public int attack;
    @SaveConstante(name = "maxHP", constructor = 3)
    public int maxHP;

    public Status(int hp, int mp, int attack, int maxHP) {
        this.hp = hp;
        this.mp = mp;
        this.attack = attack;
        this.maxHP = maxHP;
    }

    public Status copy() {
        return new Status(hp, mp, attack, maxHP);
    }
}
