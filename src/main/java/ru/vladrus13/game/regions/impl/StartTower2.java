package ru.vladrus13.game.regions.impl;

import ru.vladrus13.game.actors.ActorFactory;
import ru.vladrus13.game.items.ItemFactory;
import ru.vladrus13.game.regions.RegionFactory;
import ru.vladrus13.jgraphic.basic.components.Choose;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.returned.IntEvent;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.event.region.RegionEventFocused;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.components.Tile;
import ru.vladrus13.rpg.world.items.RegionItem;
import ru.vladrus13.rpg.world.region.Region;

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
        pirateChoose = Choose.getInstance("pirateChoose", 3, new Point(100, 100, CoordinatesType.RATIO),
                new Size(800, 800, CoordinatesType.RATIO), region, new Size(800, 100, CoordinatesType.RATIO),
                new Filler[]{new Filler(Color.WHITE), new Filler(Color.WHITE), new Filler(Color.WHITE)},
                new Filler[]{new Filler(Color.LIGHT_GRAY), new Filler(Color.LIGHT_GRAY), new Filler(Color.LIGHT_GRAY)},
                new String[]{"Hello", "Shop", "Bye"}, "Inventory", new Size(400, 0, CoordinatesType.RATIO), Color.BLACK,
                Text.TextAlign.CENTER, new Event[]{new IntEvent(IntEvent.NOTHING), new IntEvent(IntEvent.NOTHING), new IntEvent(IntEvent.NOTHING)},
                new Event[]{new IntEvent(IntEvent.NOTHING), new IntEvent(IntEvent.NOTHING), new IntEvent(IntEvent.NOTHING)}
        );
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
