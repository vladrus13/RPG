package ru.vladrus13.rpg.world.actors.region;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Background;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.world.region.WarZone;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class StatusWindow extends Frame {

    private final Background full;
    private final Background health;
    private final WarZone warZone;

    /**
     * Standard frame constructor
     *
     * @param parent parent frame
     */
    public StatusWindow(WarZone parent) {
        super("statusWindow", new Point(0, 750, CoordinatesType.RATIO), new Size(1000, 250, CoordinatesType.RATIO), parent);
        full = new Background("full", new Point(255, 5, CoordinatesType.RATIO),
                new Size(490, 300, CoordinatesType.RATIO), new Filler(new Color(100, 0, 0)), this);
        health = new Background("health", new Point(255, 5, CoordinatesType.RATIO),
                new Size(490, 300, CoordinatesType.RATIO), new Filler(new Color(255, 0, 0)), this);
        this.warZone = parent;
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        int pixelsHP = 490 * warZone.hero.realStatus.hp / warZone.hero.realStatus.maxHP;
        health.setFrame(null, new Size(pixelsHP, 300, CoordinatesType.RATIO));
        full.draw(graphics);
        health.draw(graphics);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void recalculateChildes() {
        super.recalculateChildes();
        if (full != null) {
            full.recalculate();
        }
        if (health != null) {
            health.recalculate();
        }
    }
}
