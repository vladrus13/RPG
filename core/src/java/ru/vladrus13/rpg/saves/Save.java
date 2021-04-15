package ru.vladrus13.rpg.saves;

import org.json.JSONObject;
import ru.vladrus13.rpg.world.actors.Actor;

import java.util.HashMap;
import java.util.Map;

@Savable(implemented = false)
public class Save {
    @SaveConstante(name = "regions", setNameFunction = "setRegions")
    public Map<String, Variables> regionsData = new HashMap<>();
    @SaveConstante(name = "hero")
    public Actor hero;
    @SaveConstante(name = "global")
    public Variables global = new Variables();

    public void setRegions(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            regionsData.put(key, (Variables) SaveHolder.recursiveSet(jsonObject.getJSONObject(key), Variables.class));
        }
    }
}
