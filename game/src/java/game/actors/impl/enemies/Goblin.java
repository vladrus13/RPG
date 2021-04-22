package game.actors.impl.enemies;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.actors.Enemy;
import ru.vladrus13.rpg.world.actors.Status;
import ru.vladrus13.rpg.ai.world.command.RandomAI;
import ru.vladrus13.rpg.world.region.WarZone;

public class Goblin extends Enemy {
    public Goblin(Point start, String name, WarZone region) {
        super(11, "goblin", start, name, region);
        command = 1;
        this.standardStatus = new Status(50, 0, 10, 50);
        updateStatus();
        setWarZoneAI(new RandomAI());
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Actor copy() {
        return null;
    }
}
