package ru.vladrus13.rpg.world.items.inventory;

import ru.vladrus13.rpg.world.items.Item;

import java.util.ArrayList;

/**
 * @author vladrus13 on 25.03.2021
 **/
public class Inventory {
    public final ArrayList<Items> items;

    public Inventory(ArrayList<Items> items) {
        this.items = items;
    }

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        for (Items items : this.items) {
            if (items.item.equals(item)) {
                items.count++;
                return;
            }
        }
        items.add(new Items(item, 1));
    }

    public void addItem(Items items) {
        for (Items itemsI : this.items) {
            if (itemsI.item.equals(items.item)) {
                itemsI.count += items.count;
                return;
            }
        }
        this.items.add(items);
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
        for (Items items : this.items) {
            if (items.item.equals(item)) {
                items.count--;
                if (items.count <= 0) {
                    this.items.remove(items);
                }
                return;
            }
        }
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
    }
}
