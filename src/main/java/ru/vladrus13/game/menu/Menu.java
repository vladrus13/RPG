package ru.vladrus13.game.menu;

import ru.vladrus13.core.basic.Drawn;
import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.components.Background;
import ru.vladrus13.core.basic.components.Button;
import ru.vladrus13.core.basic.components.Choose;
import ru.vladrus13.core.basic.components.Text;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.game.basic.ReturnKeys;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu extends Frame {

    public Menu(int width, int height) throws GameException {
        super(new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), new ArrayList<>(), null);
        Point startStart = new Point(10, 10, CoordinatesType.RATIO);
        Size startSize = new Size(980, 480, CoordinatesType.RATIO);
        Choose choose = new Choose(new Point(250, 250, CoordinatesType.RATIO), new Size(500, 500, CoordinatesType.RATIO), new ArrayList<>(), this);
        Button start = new Button(
                startStart.copy(),
                startSize.copy(), choose) {
            @Override
            public int keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER) {
                    return ReturnKeys.ACCEPTED;
                }
                return ReturnKeys.NOTHING;
            }

            @Override
            public int mousePressed(MouseEvent e) {
                return ReturnKeys.NOTHING;
            }
        };
        start.setBackground(new Background(startStart, startSize, Color.BLUE, start));
        start.setBackgroundChoose(new Background(startStart, startSize, Color.RED, start));
        start.setText(new Text(startStart, startSize, "Start", "Inventory", new Size(300, 0, CoordinatesType.RATIO), Color.BLACK, Text.TextAlign.CENTER, start));
        Point exitStart = new Point(10, 510, CoordinatesType.RATIO);
        Size exitSize = new Size(980, 480, CoordinatesType.RATIO);
        Button exit = new Button(
                exitStart.copy(),
                exitSize.copy(), choose) {
            @Override
            public int keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER) {
                    return ReturnKeys.ACCEPTED;
                }
                return ReturnKeys.NOTHING;
            }

            @Override
            public int mousePressed(MouseEvent e) {
                return ReturnKeys.NOTHING;
            }
        };
        exit.setBackground(new Background(exitStart, exitSize, Color.BLUE, exit));
        exit.setBackgroundChoose(new Background(exitStart, exitSize, Color.RED, exit));
        exit.setText(new Text(exitStart, exitSize, "Exit", "Inventory", new Size(300, 0, CoordinatesType.RATIO), Color.BLACK, Text.TextAlign.CENTER, exit));
        choose.addButton(start);
        choose.addButton(exit);
        frames.add(choose);
        recalculate();
    }

    public void recalculate(int width, int height) {
        size = new Size(width, height, CoordinatesType.REAL);
        recalculate();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        for (Drawn drawn : frames) {
            drawn.draw(graphics);
        }
    }

    @Override
    public int keyPressed(KeyEvent e) {
        return 0;
    }

    @Override
    public int mousePressed(MouseEvent e) {
        return 0;
    }
}
