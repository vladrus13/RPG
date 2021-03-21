package ru.vladrus13.game.world.actors.impl;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.game.basic.event.returned.ReturnEvent;
import ru.vladrus13.game.basic.event.returned.ReturnInt;
import ru.vladrus13.game.world.actors.Actor;
import ru.vladrus13.game.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Pirate extends Actor {
    public Pirate(Point start, Frame parent, Region region) {
        super("pirate", start, "pirate", parent, region);
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
    public void onStep() { }

    @Override
    public void onTrigger() {
        // TODO make menu
    }

    @Override
    public int getSpeed() {
        return 3;
    }
}
