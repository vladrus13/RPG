package ru.vladrus13.rpg.world.region;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.components.Background;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.world.World;

import java.awt.*;

public class WarZone extends Region {
    public WarZone(int id, String name, World parent) {
        super(id, name, parent);
    }

    private final Background full = new Background("full", new Point(10, 930, CoordinatesType.RATIO),
                new Size(960, 50, CoordinatesType.RATIO), new Filler(new Color(100, 0, 0)), this);
    private final Background health = new Background("health", new Point(10, 930, CoordinatesType.RATIO),
                new Size(960, 50, CoordinatesType.RATIO), new Filler(new Color(255, 0, 0)), this);

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        int pixelsHP = 960 * hero.realStatus.hp / hero.realStatus.maxHP;
        health.setFrame(null, new Size(pixelsHP, 50, CoordinatesType.RATIO));
        full.draw(graphics);
        health.draw(graphics);
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
