package ru.vladrus13.rpg.saves;

import org.json.JSONObject;

public interface Savable {
    String toSaveString();

    JSONObject toJSON();
}
