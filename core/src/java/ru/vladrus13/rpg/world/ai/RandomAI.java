package ru.vladrus13.rpg.world.ai;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.direction.DirectionService;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.ai.command.AttackCommand;
import ru.vladrus13.rpg.world.ai.command.Command;
import ru.vladrus13.rpg.world.ai.command.StepCommand;
import ru.vladrus13.rpg.world.region.WarZone;

import java.util.Random;

public class RandomAI extends WarZoneAI {

    Random random = new Random();

    @Override
    public Command getCommand(WarZone warZone, Actor actor) {
        Point currentPosition = actor.getStart();
        for (Direction direction : Direction.values()) {
            Point future = DirectionService.step(currentPosition, direction);
            Actor another = warZone.getTile(future).actor;
            if (another == null) continue;
            if (another.command != actor.command) {
                return new AttackCommand(actor, another);
            }
        }
        int direction = random.nextInt(4);
        return new StepCommand(Direction.values()[direction]);
    }
}
