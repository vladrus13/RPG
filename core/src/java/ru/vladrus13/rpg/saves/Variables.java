package ru.vladrus13.rpg.saves;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Variables implements Savable {
    public Map<String, String> variables = new HashMap<>();

    @Override
    public String toSaveString() {
        return toJSON().toString();
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject(variables);
    }
}
