package game.actors.impl;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.direction.DirectionService;
import ru.vladrus13.rpg.basic.event.region.RegionEventOnStep;
import ru.vladrus13.rpg.basic.event.world.WorldEventGameOver;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.actors.Status;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Hero extends Actor {
    public Hero(Point start, Region region, String name) {
        super(1, "hero", start, name, region);
        this.onStep = new RegionEventOnStep(this);
        this.standardStatus = new Status(100, 0, 10, 100);
        updateStatus();
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
                break;
            default:
                break;
        }
    }

    public void onStep() {
        if (onStep != null) region.invokeRegionEvent(onStep);
    }

    public void onTrigger() {
        if (onTrigger != null) region.invokeRegionEvent(onTrigger);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                Actor actor = region.getActor(DirectionService.step(start, direction));
                if (actor != null) {
                    actor.onDamage(this.realStatus.attack);
                }
                break;
            default:
                logger.info("Click: " + e.getButton());
        }
    }

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public Actor copy() {
        return new Hero(start.copy(), region, name);
    }

    @Override
    public void onDamage(int damage) {
        this.realStatus.hp -= damage;
        logger.info("Hero get " + damage + " damage. " + realStatus.hp + "/" + realStatus.maxHP + ".");
        if (realStatus.hp <= 0) {
            callEvent(new WorldEventGameOver());
        }
    }
}
