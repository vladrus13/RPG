package ru.vladrus13.rpg.world.quickmenu;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Background;
import ru.vladrus13.jgraphic.basic.components.Choose;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.factory.components.ButtonFactory;
import ru.vladrus13.jgraphic.factory.components.TextFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class QuickMenu extends Frame {

    private final Size buttonSize = new Size(500, 100, CoordinatesType.RATIO);
    private final Choose choose;

    public QuickMenu(String name, Frame parent) {
        super(name, new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
        Choose choose1;
        ButtonFactory buttonFactory = new ButtonFactory()
                .setChooseBackground(new Background("background", new Filler(Color.BLUE.darker().darker()), null))
                .setBackground(new Background("background", new Filler(Color.BLUE.darker()), null));
        TextFactory textFactory = new TextFactory()
                .setTextAlign(Text.TextAlign.LEFT)
                .setFontSize(new Size(300, 0, CoordinatesType.RATIO))
                .setNameFont("PixelFontGame")
                .setColor(Color.BLACK);
        try {
            choose1 = Choose.getInstance("quick", 2, new Point(100, 100, CoordinatesType.RATIO),
                    new Size(500, 500, CoordinatesType.RATIO), this, buttonSize.copy(),
                    new String[]{"Game", "Exit"}, buttonFactory, textFactory);
        } catch (GameException e) {
            e.printStackTrace();
            choose1 = null;
        }
        choose = choose1;
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {

    }

    @Override
    public Event keyPressed(KeyEvent keyEvent) {
        return null;
    }

    @Override
    public Event mousePressed(MouseEvent mouseEvent) {
        return null;
    }
}
