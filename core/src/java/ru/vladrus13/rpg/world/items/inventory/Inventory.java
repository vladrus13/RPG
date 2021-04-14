package ru.vladrus13.rpg.world.items.inventory;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.actors.Status;
import ru.vladrus13.rpg.world.items.Item;

import java.util.ArrayList;

/**
 * @author vladrus13 on 25.03.2021
 **/
public class Inventory implements Savable {
    public final ArrayList<Items> items;
    public Actor actor;

    public Inventory(ArrayList<Items> items) {
        this.items = items;
    }

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        addItems(new Items(item, 1));
    }

    public void addItems(Items items) {
        for (Items itemsI : this.items) {
            if (itemsI.item.equals(items.item)) {
                itemsI.count += items.count;
                return;
            }
        }
        this.items.add(items.copy());
        reloadActor();
    }

    public Items find(Item item) {
        for (Items items : this.items) {
            if (items.item.equals(item)) {
                return items;
            }
        }
        return null;
    }

    public void removeItem(Item item) {
        removeItems(new Items(item, 1));
    }

    public void removeItems(Items item) {
        for (Items items : this.items) {
            if (items.item.equals(item.item)) {
                items.count -= item.count;
                if (items.count <= 0) {
                    this.items.remove(items);
                }
                return;
            }
        }
        reloadActor();
    }

    public void reloadActor() {
        if (actor == null) return;
        Status newStatus = actor.getStandardStatus().copy();
        for (Items items : this.items) {
            items.item.update(newStatus);
        }
        actor.setRealStatus(newStatus);
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public String toSaveString() {
        return toJSON().toString();
    }

    @Override
    public JSONObject toJSON() {
        JSONArray returned = new JSONArray();
        for (Items items : this.items) {
            returned.put(items.toJSON());
        }
        JSONObject object = new JSONObject();
        object.put("array", returned);
        return object;
    }
}
