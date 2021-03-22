package ru.vladrus13.rpg.world.actors.impl;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.basic.event.returned.ReturnEvent;
import ru.vladrus13.jgraphic.basic.event.returned.ReturnInt;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Pirate extends Actor {
    public Pirate(Point start, Region region, String name) {
        super("pirate", start, name, region);
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public void onStep() {
    }

    @Override
    public void onTrigger() {
        // TODO make menu
    }

    @Override
    public int getSpeed() {
        return 3;
    }

    @Override
    public Actor copy() {
        return new Pirate(start.copy(), region, name);
    }
}
