package game;

import game.actors.ActorFactoryImpl;
import game.items.ItemFactoryImpl;
import game.regions.WorldImpl;
import ru.vladrus13.graphic.PCGraphicsAWTImpl;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.impl.IntEvent;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.Game;
import ru.vladrus13.rpg.menu.Menu;
import ru.vladrus13.rpg.world.World;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class GameImpl extends Game {

    private final Logger logger = Logger.getLogger(Game.class.getName());

    public GameImpl() {
        super(MainProperty.getInteger("window.width"), MainProperty.getInteger("window.height"));
        current = new Menu(frame.getWidth(), frame.getHeight(), this);
        ActorFactoryImpl.init();
        ItemFactoryImpl.init();
        painter();
    }

    public void update(long delay) {
        if (current != null) {
            if (current instanceof UpdatedFrame) {
                ((UpdatedFrame) current).update(delay);
            }
        }
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
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
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
                    current = new WorldImpl(frame.getWidth(), frame.getHeight(), this);
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
    public World getCurrentWorld() {
        if (current instanceof World) {
            return (World) current;
        }
        return null;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        current.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


}
