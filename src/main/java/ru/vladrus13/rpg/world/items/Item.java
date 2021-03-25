package ru.vladrus13.rpg.world.items;

import java.util.Objects;

/**
 * @author vladrus13 on 24.03.2021
 **/
public class Item {
    public final int id;
    public final String name;
    public final String description;

    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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
}
