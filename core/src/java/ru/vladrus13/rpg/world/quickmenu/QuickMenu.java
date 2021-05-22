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
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.factory.components.ButtonFactory;
import ru.vladrus13.jgraphic.factory.components.TextFactory;
import ru.vladrus13.rpg.basic.Reloaded;
import ru.vladrus13.rpg.basic.event.game.GameEventChange;
import ru.vladrus13.rpg.basic.event.world.WorldEventChange;

import java.awt.*;
import java.awt.event.KeyEvent;

public class QuickMenu extends Frame implements Reloaded {

    Choose choose;

    public QuickMenu(Frame parent) {
        super("quickmenu", new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
        Choose choose1;
        Size fullSize = new Size(1000, 1000, CoordinatesType.RATIO);
        Point fullStart = new Point(0, 0, CoordinatesType.RATIO);
        ButtonFactory buttonFactory = new ButtonFactory()
                .setChooseBackground(new Background("background", fullStart.copy(), fullSize.copy(), new Filler(Color.BLUE.darker().darker()), null))
                .setBackground(new Background("background", fullStart.copy(), fullSize.copy(), new Filler(Color.BLUE.darker()), null));
        TextFactory textFactory = new TextFactory()
                .setTextAlign(Text.TextAlign.LEFT)
                .setFontSize(new Size(300, 0, CoordinatesType.RATIO))
                .setNameFont("PixelFontGame")
                .setColor(Color.BLACK);
        try {
            Size buttonSize = new Size(1000, 200, CoordinatesType.RATIO);
            choose1 = Choose.getInstance("quick", 2, new Point(20, 20, CoordinatesType.RATIO),
                    new Size(500, 500, CoordinatesType.RATIO), this, buttonSize.copy(),
                    new String[]{"Game", "Exit"}, new Event[]{new WorldEventChange(WorldEventChange.ChangeWorldFrame.REGION),
                            new GameEventChange(GameEventChange.ChangeWorldFrame.MENU)}, new Event[]{null, null}, buttonFactory, textFactory);
        } catch (AppException e) {
            e.printStackTrace();
            choose1 = null;
        }
        choose = choose1;
        Background background = new Background("back", new Point(0, 0, CoordinatesType.RATIO),
                new Size(1000, 1000, CoordinatesType.RATIO), new Filler(Color.BLUE), this);
        addChild(background);
        addChild(choose);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        for (Frame child : childes) {
            child.draw(graphics);
        }
    }

    public void reload() {
        choose.current = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        choose.keyPressed(e);
    }
}
