package ru.vladrus13.rpg.world.actors.region;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Table;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.world.actors.Ability;

import java.util.Map;

public class AbilitiesTable extends Table {

    private final Map<String, Ability> abilities;
    @SuppressWarnings("FieldCanBeLocal")
    private final int WIDTH = 4, HEIGHT = 4;
    private int count;

    public AbilitiesTable(String name, Frame parent, Map<String, Ability> abilities) {
        super(name, new Point(0, 0, CoordinatesType.RATIO), new Size(300, 1000, CoordinatesType.RATIO), parent);
        this.abilities = abilities;
        count = abilities.size();
        makeTable();
    }

    public void makeTable() {
        int counter = 0;
        AbilityTableCell[][] images = new AbilityTableCell[WIDTH][HEIGHT];
        for (Ability ability : abilities.values()) {
            images[counter / WIDTH][counter % WIDTH] = new AbilityTableCell("ability_" + counter, this, ability);
            counter++;
        }
        setTable(images, WIDTH, HEIGHT);
        count = abilities.size();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (abilities.size() != count) {
            makeTable();
        }
        super.nonCheckingDraw(graphics);
    }
}
