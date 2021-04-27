package game.actors.impl;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.direction.DirectionService;
import ru.vladrus13.rpg.basic.event.region.RegionEventOnStep;
import ru.vladrus13.rpg.basic.event.world.WorldEventGameOver;
import ru.vladrus13.rpg.world.actors.AbilityActor;
import ru.vladrus13.rpg.world.actors.AbilitySelf;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.actors.Status;
import ru.vladrus13.rpg.world.factory.AbilityFactory;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Hero extends Actor {
    public Hero(Point start, Region region, String name) {
        super(1, "hero", start, name, region);
        this.onStep = new RegionEventOnStep(this);
        this.standardStatus = new Status(100, 0, 10, 100);
        try {
            this.abilities = new HashMap<>(Map.of("Splash", AbilityFactory.get(1), "Heal", AbilityFactory.get(2)));
        } catch (AppException e) {
            Writer.printStackTrace(Logger.getLogger(Hero.class.getName()), e);
        }
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
            case KeyEvent.VK_H:
                ((AbilitySelf) abilities.get("Heal")).activate(this, region);
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
                    ((AbilityActor) abilities.get("Splash")).activate(this, actor, region);
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
    public void onPhysical(int damage) {
        this.realStatus.hp -= damage;
        if (realStatus.hp <= 0) {
            callEvent(new WorldEventGameOver());
        }
    }
}
