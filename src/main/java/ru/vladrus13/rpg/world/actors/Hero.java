package ru.vladrus13.rpg.world.actors;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.jgraphic.basic.event.returned.ReturnEvent;
import ru.vladrus13.jgraphic.basic.event.returned.ReturnInt;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Hero extends Actor {
    public Hero(Point start, Region region) {
        super("hero", start, "hero", region);
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
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
            default:
                break;
        }
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public void onStep() {
        region.onStep(this, start);
    }

    @Override
    public void onTrigger() {
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return null;
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
