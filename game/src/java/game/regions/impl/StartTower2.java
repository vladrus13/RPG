package game.regions.impl;

import game.actors.ActorFactory;
import game.items.ItemFactory;
import game.items.impl.weapons.TinSword;
import game.regions.RegionFactory;
import ru.vladrus13.jgraphic.basic.components.Background;
import ru.vladrus13.jgraphic.basic.components.Choose;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.impl.ChooseEvent;
import ru.vladrus13.jgraphic.basic.event.impl.CollectionEvent;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.factory.components.ButtonFactory;
import ru.vladrus13.jgraphic.factory.components.TextFactory;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.event.region.RegionEventFocused;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.components.Tile;
import ru.vladrus13.rpg.world.items.inventory.Items;
import ru.vladrus13.rpg.world.places.Barter;
import ru.vladrus13.rpg.world.places.Shop;
import ru.vladrus13.rpg.world.region.Region;
import ru.vladrus13.rpg.world.region.RegionItem;
import ru.vladrus13.rpg.world.region.ShopWorld;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author vladrus13 on 26.03.2021
 **/
public class StartTower2 {

    private final static int tileSize = MainProperty.getInteger("world.region.tileSize");

    private static void setActors(Region region) throws GameException {
        Actor pirate = ActorFactory.createActor("pirate", new Point(6L * tileSize, 4L * tileSize), region);
        Choose pirateChoose;
        Shop pirateShop = new Shop(new ArrayList<>(Collections.singletonList(
                new Barter(new ArrayList<>(Collections.singletonList(
                        new Items(TinSword.getInstance(), 1))),
                        new ArrayList<>(Collections.singletonList(new Items(TinSword.getInstance(), 1))), 5
                ))));
        ShopWorld shopWorld = new ShopWorld("pirate", region, pirateShop);
        Size fullSize = new Size(1000, 1000, CoordinatesType.RATIO);
        Point fullStart = new Point(0, 0, CoordinatesType.RATIO);
        ButtonFactory buttonFactory = new ButtonFactory()
                .setBackground(new Background("back", fullStart.copy(), fullSize.copy(), new Filler(Color.WHITE), null))
                .setChooseBackground(new Background("chBack", fullStart.copy(), fullSize.copy(), new Filler(Color.LIGHT_GRAY), null));
        TextFactory textFactory = new TextFactory()
                .setNameFont("Inventory")
                .setColor(Color.BLACK)
                .setFontSize(new Size(300, 0, CoordinatesType.RATIO))
                .setTextAlign(Text.TextAlign.CENTER);
        CollectionEvent byeEvent = new CollectionEvent();
        byeEvent.add(new ChooseEvent(ChooseEvent.END_CHOOSE));
        CollectionEvent shopEvent = new CollectionEvent();
        shopEvent.add(new RegionEventFocused(shopWorld, true, false));
        pirateChoose = Choose.getInstance("pirateChoose", 3, new Point(100, 100, CoordinatesType.RATIO),
                new Size(800, 800, CoordinatesType.RATIO), region, new Size(800, 100, CoordinatesType.RATIO),
                new String[]{"Hello", "Shop", "Bye"}, new Event[]{null,
                        shopEvent, byeEvent}, new Event[]{null, null, null}, buttonFactory, textFactory);

        pirate.setOnTrigger(new RegionEventFocused(pirateChoose, true, false));
        region.setActors(new ArrayList<>(Collections.singletonList(pirate)));
    }

    private static void setOnStep(Region region) {
        region.setOnStep(new WorldEventTeleport(1, new Point(tileSize * 3L, tileSize), Direction.LEFT), new Point(1, 1));
        region.setOnStep(new WorldEventTeleport(3, new Point(tileSize, tileSize), Direction.DOWN), new Point(5, 1));
    }

    private static void setItems(Region region) throws GameException {
        region.setItems(new ArrayList<>(Collections.singletonList(new RegionItem(new Point(8L * tileSize, tileSize), region, ItemFactory.get(1)))));
    }

    public static Region getInstance(World parent) throws GameException {
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 3, 0, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 2, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        ArrayList<ArrayList<Tile>> tiles = RegionFactory.getTiles(map, tileSize, parent);
        Region region = (new Region(2, "2", parent)).setTiles(tiles);
        setOnStep(region);
        setItems(region);
        setActors(region);
        return region;
    }
}
