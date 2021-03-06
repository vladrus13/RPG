package ru.vladrus13.game;

import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.game.world.World;
import ru.vladrus13.core.property.MainProperty;
import ru.vladrus13.core.services.GameService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

public class Game extends JPanel implements ActionListener, MouseListener, KeyListener {

    Logger logger = Logger.getLogger(Game.class.getName());
    JFrame frame;
    World world;

    public Game() throws GameException {
        logger.info("Run class \"Game\"");
        GameService.setGame(this);
        int width = MainProperty.getInteger("window.width");
        int height = MainProperty.getInteger("window.height");
        frame = new JFrame(MainProperty.get("window.name"));
        frame.setSize(width, height);
        frame.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        world = new World(frame.getWidth(), frame.getHeight());
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                world.recalculate(frame.getWidth(), frame.getHeight());
            }
        });
        frame.setVisible(true);
        frame.add(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        super.paintComponents(g);
        world.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
