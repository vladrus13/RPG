package game.actors.impl;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Pirate extends Actor {
    public Pirate(Point start, Region region, String name) {
        super("pirate", start, name, region);
    }

    @Override
    public void keyPressed(KeyEvent e) {

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
        return new Pirate(start.copy(), region, name);
    }
}
