package ru.vladrus13.rpg.world.items;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.world.actors.ActorFactory;
import ru.vladrus13.rpg.world.components.Tile;
import ru.vladrus13.rpg.world.components.TileFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @author vladrus13 on 24.03.2021
 **/
public class ItemFactory {

    private final static Logger logger = Logger.getLogger(ItemFactory.class.getName());

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("internationalization.items", MainProperty.getLocale());

    public static Item get(int id) throws GameException {
        try {
            return (Item) ItemFactory.class.getDeclaredMethod("get" + id)
                    .invoke(ItemFactory.class);
        } catch (NoSuchMethodException e) {
            throw new GameException("No tile with id: " + id);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new GameException(e);
        }
    }

    private static Item get1() {
        return new Item(1, resourceBundle.getString("1.name"), resourceBundle.getString("1.description"));
    }
}
