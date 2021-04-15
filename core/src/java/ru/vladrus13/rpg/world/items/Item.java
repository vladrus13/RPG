package ru.vladrus13.rpg.world.items;

import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.saves.SaveConstante;
import ru.vladrus13.rpg.world.actors.Status;

import java.util.Objects;

@Savable(implemented = false)
public abstract class Item {
    @SaveConstante(name = "id", constructor = 0)
    public final int id;
    @SaveConstante(name = "name", constructor = 1)
    public final String name;
    @SaveConstante(name = "description", constructor = 2)
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
}
