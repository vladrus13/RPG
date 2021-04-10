package ru.vladrus13.rpg.world.items.inventory;

import ru.vladrus13.rpg.world.items.Item;

import java.util.ArrayList;

/**
 * @author vladrus13 on 25.03.2021
 **/
public class Inventory {
    public final ArrayList<ItemType> items;

    public Inventory(ArrayList<ItemType> items) {
        this.items = items;
    }

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        for (ItemType itemType : items) {
            if (itemType.item.equals(item)) {
                itemType.count++;
            }
        }
    }

    public void addItem(ItemType itemType) {
        for (ItemType itemTypeI : items) {
            if (itemTypeI.item.equals(itemType.item)) {
                itemTypeI.count += itemType.count;
            }
        }
    }

    public ItemType find(Item item) {
        for (ItemType itemType : items) {
            if (itemType.item.equals(item)) {
                return itemType;
            }
        }
        return null;
    }
}
