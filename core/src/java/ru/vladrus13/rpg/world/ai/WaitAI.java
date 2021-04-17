package ru.vladrus13.rpg.world.ai;

import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.ai.command.Command;
import ru.vladrus13.rpg.world.ai.command.WaitCommand;
import ru.vladrus13.rpg.world.region.WarZone;

public class WaitAI extends WarZoneAI {
    @Override
    public Command getCommand(WarZone warZone, Actor actor) {
        return new WaitCommand(1000);
    }
}
