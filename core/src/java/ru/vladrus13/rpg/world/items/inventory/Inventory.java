package ru.vladrus13.rpg.world.items.inventory;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.saves.SaveConstante;
import ru.vladrus13.rpg.saves.SaveHolder;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.actors.Status;
import ru.vladrus13.rpg.world.items.Item;

import java.util.ArrayList;

@Savable(implemented = true)
public class Inventory {
    @SaveConstante(name = "items", constructor = 0)
    public final ArrayList<Items> items;
    public Actor actor;

    public Inventory(ArrayList<Items> items) {
        this.items = items;
    }

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public static Inventory getInstance(Object object) {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException("Inventory must be instanced from JSONObject");
        }
        JSONArray jsonArray = ((JSONObject) object).getJSONArray("items");
        ArrayList<Items> items = new ArrayList<>();
        for (Object jsonObject : jsonArray) {
            items.add((Items) SaveHolder.recursiveSet(jsonObject, Items.class));
        }
        return new Inventory(items);
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
}
