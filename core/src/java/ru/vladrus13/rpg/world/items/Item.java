package ru.vladrus13.rpg.world.items;

import org.json.JSONObject;
import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.world.actors.Status;

import java.util.Objects;

/**
 * @author vladrus13 on 24.03.2021
 **/
public abstract class Item implements Savable {
    public final int id;
    public final String name;
    public final String description;

    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static int getId() {
        throw new UnsupportedOperationException("Can't get instance of abstract object");
    }

    public static Item getInstance() {
        throw new UnsupportedOperationException("Can't get instance of abstract object");
    }

    public abstract Item copy();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void update(Status status) {
    }

    @Override
    public String toSaveString() {
        return String.valueOf(id);
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject(id);
    }
}
