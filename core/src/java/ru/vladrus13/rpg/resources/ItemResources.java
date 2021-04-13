package ru.vladrus13.rpg.resources;

import ru.vladrus13.jgraphic.property.MainProperty;

import java.util.ResourceBundle;

public class ItemResources {
    public final static ResourceBundle resourceBundle = ResourceBundle.getBundle("internationalization.items", MainProperty.getLocale());

    public static String getBundle(String s) {
        return resourceBundle.getString(s);
    }
}
