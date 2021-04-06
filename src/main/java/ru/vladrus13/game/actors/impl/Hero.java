package ru.vladrus13.game.actors.impl;

import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.impl.IntEvent;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.event.region.RegionEventOnStep;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Hero extends Actor {
    public Hero(Point start, Region region) {
        super("hero", start, "hero", region);
        this.onStep = new RegionEventOnStep(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                makeMove(Direction.UP);
                break;
            case KeyEvent.VK_LEFT:
                makeMove(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                makeMove(Direction.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                makeMove(Direction.DOWN);
                break;
            case KeyEvent.VK_ENTER:
                region.onActivate(this, start);
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public int getSpeed() {
        return 3;
    }

    @Override
    public Actor copy() {
        return new Hero(start.copy(), region);
    }
}
