package ru.vladrus13.rpg.dialog;

import ru.vladrus13.rpg.world.actors.Actor;

import java.awt.*;

public class DialogBean {
    public final Actor actor;
    public final Color nameColor;
    public final String text;

    public DialogBean(Actor actor, Color nameColor, String text) {
        this.actor = actor;
        this.nameColor = nameColor;
        this.text = text;
    }
}
