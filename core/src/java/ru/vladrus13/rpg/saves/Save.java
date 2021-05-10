package ru.vladrus13.rpg.saves;

import org.json.JSONObject;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.util.HashMap;
import java.util.Map;

@Savable(implemented = false)
public class Save {
    @SaveConstante(name = "regions", setNameFunction = "setRegions")
    public final Map<String, Variables> regionsData = new HashMap<>();
    @SaveConstante(name = "global")
    public final Variables global = new Variables();
    @SaveConstante(name = "hero")
    public Actor hero;

    public void setRegions(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            regionsData.put(key, (Variables) SaveHolder.recursiveSet(jsonObject.getJSONObject(key), Variables.class));
        }
    }

    public Variables getVariables(Region region) {
        return regionsData.get(region.name);
    }

    public String get(Region region, String key) {
        Variables variables = getVariables(region);
        if (variables == null) {
            return null;
        }
        return variables.variables.get(key);
    }

    public String get(String key) {
        return global.variables.get(key);
    }

    public Long getLong(String key) {
        String getting = get(key);
        if (getting == null) {
            return null;
        }
        return Long.parseLong(getting);
    }

    public Integer getInt(String key) {
        String getting = get(key);
        if (getting == null) {
            return null;
        }
        return Integer.parseInt(getting);
    }

    public void set(Region region, String key, String value) {
        Variables variables = getVariables(region);
        if (variables == null) {
            regionsData.put(region.name, new Variables());
        }
        variables = getVariables(region);
        variables.variables.put(key, value);
    }

    public void set(String key, String value) {
        global.set(key, value);
    }

    public void setHero(Actor hero) {
        this.hero = hero;
    }


}
