package ru.vladrus13.rpg.saves;

import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

public class SaveHolder {
    public static Save save;

    public static Save getSave() {
        return save;
    }

    public static void setSave(Save save) {
        SaveHolder.save = save;
    }

    public static Variables getVariables(Region region) {
        return save.regionsData.get(region.name);
    }

    public static String getVariable(Region region, String key) {
        Variables variables = getVariables(region);
        if (variables == null) {
            return null;
        }
        return variables.variables.get(key);
    }

    public static void setVariable(Region region, String key, String value) {
        Variables variables = getVariables(region);
        if (variables == null) {
            save.regionsData.put(region.name, new Variables());
        }
        variables = getVariables(region);
        variables.variables.put(key, value);
    }

    public static void setHero(Actor hero) {
        save.hero = hero;
    }
}
