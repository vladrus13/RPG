package ru.vladrus13.rpg.world.quickmenu;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.factory.components.TextFactory;
import ru.vladrus13.rpg.basic.Reloaded;
import ru.vladrus13.rpg.basic.event.game.GameEventChange;
import ru.vladrus13.rpg.basic.event.region.RegionEventDrawing;
import ru.vladrus13.rpg.basic.event.world.WorldEventDrawing;

import java.awt.*;

public class GameOver extends UpdatedFrame implements Reloaded {

    public final Text text;
    public int counter;

    /**
     * Standard frame constructor
     *
     * @param parent parent frame
     */
    public GameOver(Frame parent) {
        super("gameOver", new Point(0, 400, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
        Text text1;
        TextFactory textFactory = new TextFactory()
                .setTextAlign(Text.TextAlign.CENTER)
                .setColor(Color.BLACK)
                .setFontSize(new Size(100, 0, CoordinatesType.RATIO))
                .setNameFont("MK-90");
        try {
            text1 = textFactory.getInstance("gameOverText", "GAME OVER", this);
            text1.setFrame(new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO));
        } catch (AppException e) {
            e.printStackTrace();
            text1 = null;
        }
        text = text1;
        counter = 5000;
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        counter -= delay;
        if (counter < 0) {
            callEvent(new WorldEventDrawing(this, true, true, true));
            callEvent(new GameEventChange(GameEventChange.ChangeWorldFrame.MENU));
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        text.draw(graphics);
    }

    public void reload() {
        counter = 5000;
    }
}
