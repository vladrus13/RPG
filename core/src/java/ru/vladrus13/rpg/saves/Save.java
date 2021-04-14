package ru.vladrus13.rpg.saves;

import ru.vladrus13.rpg.world.actors.Actor;

import java.util.HashMap;
import java.util.Map;

public class Save implements Savable {
    public Map<String, Variables> regionsData = new HashMap<>();
    public Actor hero;

    @Override
    public String toSaveString() {
        return null;
    }
}
