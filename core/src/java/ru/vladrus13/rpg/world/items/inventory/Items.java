package ru.vladrus13.rpg.world.items.inventory;

import org.json.JSONObject;
import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.world.items.Item;

import java.util.Objects;

/**
 * @author vladrus13 on 25.03.2021
 **/
public class Items implements Savable {
    public final Item item;
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

    @Override
    public String toSaveString() {
        return item.toSaveString();
    }

    @Override
    public JSONObject toJSON() {
        JSONObject returned = new JSONObject();
        returned.put("item", item.toJSON());
        returned.put("count", count);
        return returned;
    }
}
