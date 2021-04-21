package ru.vladrus13.rpg.menu;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Button;
import ru.vladrus13.jgraphic.basic.components.*;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.impl.IntEvent;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Menu extends Frame {

    private final Choose choose;
    private final Game game;

    public Menu(int width, int height, Game game) {
        super("menu", new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), null);
        this.game = game;
        Point startStart = new Point(10, 10, CoordinatesType.RATIO);
        Size startSize = new Size(980, 480, CoordinatesType.RATIO);
        choose = new Choose("menuChoose", new Point(250, 250, CoordinatesType.RATIO), new Size(500, 500, CoordinatesType.RATIO), this);
        addChild(choose);
        try {
            Button start = new ClassicButton("start", startStart.copy(), startSize.copy(), choose).setEventKey(new IntEvent(IntEvent.TO_WORLD));
            start.setBackground(new Background("agree", startStart, startSize, new Filler(Color.BLUE), start));
            start.setBackgroundChoose(new Background("disagree", startStart, startSize, new Filler(Color.RED), start));
            ResourceBundle resourceBundle = ResourceBundle.getBundle("internationalization.menu", MainProperty.getLocale());
            start.setText(new Text("text", startStart, startSize, resourceBundle.getString("start"), "Inventory", new Size(300, 0, CoordinatesType.RATIO), Color.BLACK, Text.TextAlign.CENTER, start));
            Point exitStart = new Point(10, 510, CoordinatesType.RATIO);
            Size exitSize = new Size(980, 480, CoordinatesType.RATIO);
            Button exit = new ClassicButton("exit", exitStart.copy(), exitSize.copy(), choose).setEventKey(new IntEvent(IntEvent.END_GAME));
            exit.setBackground(new Background("agree", exitStart, exitSize, new Filler(Color.BLUE), exit));
            exit.setBackgroundChoose(new Background("disagree", exitStart, exitSize, new Filler(Color.RED), exit));
            exit.setText(new Text("text", exitStart, exitSize, resourceBundle.getString("exit"), "Inventory", new Size(300, 0, CoordinatesType.RATIO), Color.BLACK, Text.TextAlign.CENTER, exit));
            choose.addButton(start);
            choose.addButton(exit);
            focused.add(choose);
        } catch (GameException e) {
            Logger logger = Logger.getLogger(Menu.class.getName());
            Writer.printStackTrace(logger, e);
        }
        recalculateChildes();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        choose.draw(graphics);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        focused.getFirst().keyPressed(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        focused.getFirst().mousePressed(e);
    }

    @Override
    public void callEvent(Event event) {
        game.callEvent(event);
    }
}
