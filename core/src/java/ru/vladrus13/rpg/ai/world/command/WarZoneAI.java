package ru.vladrus13.rpg.ai.world.command;

import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.ai.world.command.Command;
import ru.vladrus13.rpg.world.region.WarZone;

public abstract class WarZoneAI {
    public abstract Command getCommand(WarZone warZone, Actor actor);
}
