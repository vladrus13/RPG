package ru.vladrus13.rpg;

import ru.vladrus13.graphic.PCGraphicsAWTImpl;
import ru.vladrus13.jgraphic.App;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.event.game.GameEvent;
import ru.vladrus13.rpg.basic.event.game.GameEventChange;
import ru.vladrus13.rpg.basic.event.game.GameEventExit;
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
        if (event instanceof GameEvent) {
            if (event instanceof GameEventExit) {
                isInterrupted = true;
                current = null;
                System.exit(0);
            }
            if (event instanceof GameEventChange) {
                GameEventChange gameEventChange = (GameEventChange) event;
                switch (gameEventChange.type) {
                    case MENU:
                        current = new Menu(frame.getWidth(), frame.getHeight(), this);
                        break;
                    case WORLD:
                        current = new World(frame.getWidth(), frame.getHeight(), this);
                        break;
                    default:
                        throw new IllegalStateException("Can't change to " + gameEventChange.type.name());
                }
            }
            return;
        }
        throw new IllegalStateException("Unknown event: " + event);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        current.mousePressed(e);
    }
}
