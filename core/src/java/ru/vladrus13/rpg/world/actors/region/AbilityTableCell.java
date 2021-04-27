package ru.vladrus13.rpg.world.actors.region;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Background;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.world.actors.Ability;

import java.awt.*;

public class AbilityTableCell extends Frame {

    public Ability ability;
    public Background background;
    public Background image;

    public AbilityTableCell(String name, Frame parent, Ability ability) {
        super(name, new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
        if (ability == null) {
            return;
        }
        this.ability = ability;
        image = new Background("ability_" + ability.getName(), new Point(0, 0, CoordinatesType.RATIO),
                new Size(1000, 1000, CoordinatesType.RATIO), new Filler(ability.icon), this);
        background = new Background("background_ability_" + ability.getName(), new Point(0, 0, CoordinatesType.RATIO),
                new Size(1000, 1000, CoordinatesType.RATIO), new Filler(new Color(0, 0, 0, 100)), this);
        childes.add(image);
        childes.add(background);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (ability == null) {
            return;
        }
        long position = 1000 * ability.current / ability.coolDown;
        background.setFrame(new Point(position, 0, CoordinatesType.RATIO), new Size(1000 - position, 1000, CoordinatesType.RATIO));
        image.draw(graphics);
        background.draw(graphics);
    }
}
