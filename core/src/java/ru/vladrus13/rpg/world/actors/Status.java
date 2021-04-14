package ru.vladrus13.rpg.world.actors;

import org.json.JSONObject;
import ru.vladrus13.rpg.saves.Savable;

public class Status implements Savable {
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

    @Override
    public String toSaveString() {
        return toJSON().toString();
    }

    @Override
    public JSONObject toJSON() {
        JSONObject returned = new JSONObject();
        returned.put("hp", hp);
        returned.put("mp", mp);
        returned.put("attack", attack);
        return returned;
    }
}
