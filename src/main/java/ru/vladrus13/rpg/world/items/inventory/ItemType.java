package ru.vladrus13.rpg.world.items.inventory;

import ru.vladrus13.rpg.world.items.Item;

import java.util.Objects;

/**
 * @author vladrus13 on 25.03.2021
 **/
public class ItemType {
    public final Item item;
    public int count;

    public ItemType(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemType itemType = (ItemType) o;
        return Objects.equals(item, itemType.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }
}
