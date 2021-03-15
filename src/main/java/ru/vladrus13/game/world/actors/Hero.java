package ru.vladrus13.game.world.actors;

import ru.vladrus13.core.bean.Point;
import ru.vladrus13.game.basic.direction.Direction;
import ru.vladrus13.game.basic.event.returned.ReturnEvent;
import ru.vladrus13.game.basic.event.returned.ReturnInt;
import ru.vladrus13.game.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Hero extends Actor {
    public Hero(Point start, Region region) {
        super(start, "Hero", "hero", region, region);
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
    public ReturnEvent mousePressed(MouseEvent e) {
        return null;
    }

    @Override
    public int getSpeed() {
        return 3;
    }
}
