package ru.vladrus13.rpg.world.items;

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


}
