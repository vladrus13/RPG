package game.actors.impl.enemies;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.actors.Enemy;
import ru.vladrus13.rpg.world.actors.Status;
import ru.vladrus13.rpg.world.region.WarZone;

public class Dummy extends Enemy {
    public Dummy(Point start, String name, WarZone region) {
        super(10, "dummy", start, name, region);
        this.standardStatus = new Status(100, 0, 0, 100);
        updateStatus();
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Actor copy() {
        return new Dummy(start.copy(), name, (WarZone) region);
    }
}
