package ru.vladrus13.rpg.world.items.inventory;

import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.saves.SaveConstante;
import ru.vladrus13.rpg.world.items.Item;

import java.util.Objects;

/**
 * @author vladrus13 on 25.03.2021
 **/
@Savable(implemented = false)
public class Items {
    @SaveConstante(name = "item", constructor = 0)
    public final Item item;
    @SaveConstante(name = "count", constructor = 1)
    public int count;

    public Items(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Objects.equals(item, items.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }

    public Items copy() {
        return new Items(item.copy(), count);
    }
}
