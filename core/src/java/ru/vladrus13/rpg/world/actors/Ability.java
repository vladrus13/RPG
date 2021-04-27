package ru.vladrus13.rpg.world.actors;

import org.json.JSONObject;
import ru.vladrus13.jgraphic.basic.Updated;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.resources.ImageLoader;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.resources.ImageGameLoader;
import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.saves.SaveConstante;
import ru.vladrus13.rpg.world.factory.AbilityFactory;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.logging.Logger;

@Savable(implemented = true)
public abstract class Ability implements Updated {
    private static final Logger logger = Logger.getLogger(Ability.class.getName());
    private static final Path abilitiesIconPath = Path.of("graphic").resolve("world").resolve("abilities");

    @SaveConstante(name = "id", constructor = 1)
    public int id = -1;
    public long coolDown;
    public long current = 0;
    public BufferedImage icon;

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

    public boolean isNotUsable() {
        boolean isUsable = current >= coolDown;
        if (isUsable) current = 0;
        return !isUsable;
    }

    @Override
    public void update(long delay) {
        if (current < coolDown) {
            current += delay;
        }
    }

    public void loadIcon() {
        try {
            icon = ImageLoader.load(abilitiesIconPath.resolve(getName() + ".png"));
        } catch (GameException e) {
            Writer.printStackTrace(logger, e);
        }
    }
}
