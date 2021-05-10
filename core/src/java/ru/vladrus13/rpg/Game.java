package ru.vladrus13.rpg;

import ru.vladrus13.graphic.PCGraphicsAWTImpl;
import ru.vladrus13.jgraphic.App;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.impl.IntEvent;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.menu.Menu;
import ru.vladrus13.rpg.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Game extends App {
    public Game() {
        super(MainProperty.getInteger("window.width"), MainProperty.getInteger("window.height"));
        current = new Menu(frame.getWidth(), frame.getHeight(), this);
        painter();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        super.paintComponents(g);
        if (current != null) {
            current.draw(new PCGraphicsAWTImpl(g));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        current.keyPressed(e);
    }

    public void callEvent(Event event) {
        if (event instanceof IntEvent) {
            int code = ((IntEvent) event).getEvent();
            switch (code) {
                case IntEvent.TO_WORLD:
                    current = new World(frame.getWidth(), frame.getHeight(), this);
                    break;
                case IntEvent.TO_MENU:
                    current = new Menu(frame.getWidth(), frame.getHeight(), this);
                    break;
                case IntEvent.END_GAME:
                    isInterrupted = true;
                    current = null;
                    System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        current.mousePressed(e);
    }
}
