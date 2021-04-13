package ru.vladrus13.rpg.world.actors;

public class Status {
    public int hp;
    public int mp;
    public int attack;

    public Status() {
        this.hp = 1;
        this.attack = 0;
        this.mp = 0;
    }

    public Status(int hp, int mp, int attack) {
        this.hp = hp;
        this.mp = mp;
        this.attack = attack;
    }

    public Status copy() {
        return new Status(hp, mp, attack);
    }
}
