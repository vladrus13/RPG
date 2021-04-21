package ru.vladrus13.rpg.world.actors;

import org.json.JSONObject;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.saves.SaveConstante;
import ru.vladrus13.rpg.world.factory.AbilityFactory;

import java.util.logging.Logger;

@Savable(implemented = true)
public abstract class Ability {
    private static final Logger logger = Logger.getLogger(Ability.class.getName());

    @SaveConstante(name = "id", constructor = 1)
    public int id = -1;

    public static Ability getInstance() {
        throw new UnsupportedOperationException("Can't get instance from abstract class");
    }

    public static Ability getInstance(Object object) {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException("Inventory must be instanced from JSONObject");
        }
        try {
            return AbilityFactory.get(((JSONObject) object).getInt("id"));
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
        return null;
    }

    public abstract String getName();

    public abstract int getId();
}
