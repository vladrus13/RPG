package ru.vladrus13.rpg.saves;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Savable(implemented = false)
public class Variables {
    @SaveConstante(name = "variables", setNameFunction = "setVariables")
    public Map<String, String> variables = new HashMap<>();

    public String get(String key) {
        return variables.get(key);
    }

    public long getLong(String key) {
        return Long.parseLong(variables.get(key));
    }

    public int getInt(String key) {
        return Integer.parseInt(variables.get(key));
    }

    public void setVariables(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            variables.put(key, jsonObject.getString(key));
        }
    }
}
