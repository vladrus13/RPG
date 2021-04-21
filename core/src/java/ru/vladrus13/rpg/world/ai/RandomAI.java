package ru.vladrus13.rpg.world.ai;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.direction.DirectionService;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.ai.command.AttackCommand;
import ru.vladrus13.rpg.world.ai.command.Command;
import ru.vladrus13.rpg.world.ai.command.StepCommand;
import ru.vladrus13.rpg.world.factory.AbilityFactory;
import ru.vladrus13.rpg.world.region.WarZone;

import java.util.Random;
import java.util.logging.Logger;

public class RandomAI extends WarZoneAI {

    Random random = new Random();
    private final Logger logger = Logger.getLogger(RandomAI.class.getName());

    @Override
    public Command getCommand(WarZone warZone, Actor actor) {
        Point currentPosition = actor.getStart();
        for (Direction direction : Direction.values()) {
            Point future = DirectionService.step(currentPosition, direction);
            Actor another = warZone.getTile(future).actor;
            if (another == null) continue;
            if (another.command != actor.command) {
                try {
                    return new AttackCommand(actor, another, AbilityFactory.get(1));
                } catch (GameException e) {
                    Writer.printStackTrace(logger, e);
                    return null;
                }
            }
        }
        int direction = random.nextInt(4);
        return new StepCommand(Direction.values()[direction]);
    }
}
