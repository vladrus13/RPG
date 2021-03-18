package ru.vladrus13.game;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.property.MainProperty;
import ru.vladrus13.core.services.GameService;
import ru.vladrus13.core.utils.Writer;
import ru.vladrus13.game.basic.event.returned.ReturnEvent;
import ru.vladrus13.game.basic.event.returned.ReturnInt;
import ru.vladrus13.game.menu.Menu;
import ru.vladrus13.game.world.World;
import ru.vladrus13.graphic.PCGraphicsAWTImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

public class Game extends JPanel implements ActionListener, MouseListener, KeyListener {

    private final Logger logger = Logger.getLogger(Game.class.getName());
    private final JFrame frame;
    private Frame current;
    boolean isInterrupted = false;

    public Game() {
        logger.info("Run class: Game");
        GameService.setGame(this);
        int width = MainProperty.getInteger("window.width");
        int height = MainProperty.getInteger("window.height");
        frame = new JFrame(MainProperty.get("window.name"));
        frame.setSize(width, height);
        frame.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        current = new Menu(frame.getWidth(), frame.getHeight());
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Insets insets = frame.getInsets();
                current.recalculate(
                        frame.getWidth() - insets.left - insets.right,
                        frame.getHeight() - insets.top - insets.bottom);
            }
        });
        frame.setVisible(true);
        frame.add(this);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        painter();
    }

    private void painter() {
        // TODO make FPS better mode
        long targetTime = 1000 / MainProperty.getInteger("window.maxFPS");
        long previousTime = System.currentTimeMillis() - targetTime;
        while (!isInterrupted) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - previousTime < targetTime) {
                try {
                    Thread.sleep(previousTime + targetTime - currentTime);
                } catch (InterruptedException e) {
                    Writer.printStackTrace(logger, e);
                }
            }
            update(System.currentTimeMillis() - previousTime);
            previousTime = System.currentTimeMillis();
            repaint();
        }
    }

    public void update(long delay) {
        if (current instanceof UpdatedFrame) {
            ((UpdatedFrame) current).update(delay);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        super.paintComponents(g);
        current.draw(new PCGraphicsAWTImpl(g));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ReturnEvent event = current.keyPressed(e);
        if (event instanceof ReturnInt) {
            int code = ((ReturnInt) event).getEvent();
            switch (code) {
                case ReturnInt.TO_WORLD:
                    current = new World(frame.getWidth(), frame.getHeight());
                    break;
                case ReturnInt.TO_MENU:
                    current = new Menu(frame.getWidth(), frame.getHeight());
                    break;
                case ReturnInt.END_GAME:
                    isInterrupted = true;
                    current = null;
                    System.exit(0);
            }
        }
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
        repaint();
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
