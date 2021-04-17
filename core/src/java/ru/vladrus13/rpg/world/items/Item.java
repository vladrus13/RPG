package ru.vladrus13.rpg.world.items;

import org.json.JSONObject;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.saves.SaveConstante;
import ru.vladrus13.rpg.world.actors.Status;
import ru.vladrus13.rpg.world.factory.ItemFactory;

import java.util.Objects;
import java.util.logging.Logger;

@Savable(implemented = true)
public abstract class Item {
    private static final Logger logger = Logger.getLogger(Item.class.getName());
    @SaveConstante(name = "id", constructor = 1)
    public final int id;
    public final String name;
    public final String description;

    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Item getInstance(Object object) {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException("Inventory must be instanced from JSONObject");
        }
        try {
            return ItemFactory.get(((JSONObject) object).getInt("id"));
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        return null;
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
