package ru.vladrus13.rpg.world.region;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.actors.region.StatusWindow;

public class WarZone extends Region {

    public boolean isShowStatusWindow = true;
    public final StatusWindow statusWindow;

    public WarZone(int id, String name, World parent) {
        super(id, name, parent);
        statusWindow = new StatusWindow(this);
        childes.add(statusWindow);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        if (isShowStatusWindow) {
            statusWindow.draw(graphics);
        }
    }
}
