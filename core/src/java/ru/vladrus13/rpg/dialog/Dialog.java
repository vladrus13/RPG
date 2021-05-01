package ru.vladrus13.rpg.dialog;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Background;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.utils.Writer;
import ru.vladrus13.rpg.basic.event.region.RegionEventDrawing;
import ru.vladrus13.rpg.resources.ActorResources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

public class Dialog extends Frame {

    public final ArrayList<DialogBean> dialogBeans = new ArrayList<>();
    public final Background background;
    public int number = 0;
    public Text text;
    public Text name;
    public Background imageActor;

    public Dialog(Frame parent) {
        super("dialog", new Point(0, 625, CoordinatesType.RATIO), new Size(1000, 375, CoordinatesType.RATIO), parent);
        background = new Background("background",
                new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO),
                new Filler(new Color(0, 0, 0, 100)), this);
    }

    public void renew() {
        number = 0;
        if (!dialogBeans.isEmpty()) {
            try {
                reload();
            } catch (AppException e) {
                Writer.printStackTrace(logger, e);
            }
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (text != null) {
            background.draw(graphics);
            text.draw(graphics);
            name.draw(graphics);
            if (imageActor != null) {
                imageActor.draw(graphics);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                number++;
                if (number >= dialogBeans.size()) {
                    callEvent(new RegionEventDrawing(this, true, true, true));
                    return;
                }
                try {
                    reload();
                } catch (AppException AppException) {
                    Writer.printStackTrace(logger, AppException);
                }
                break;
            default:
                break;
        }
    }

    public void reload() throws AppException {
        text = null;
        name = null;
        imageActor = null;
        DialogBean dialogBean = dialogBeans.get(number);
        text = new Text("text", new Point(10, 250, CoordinatesType.RATIO), new Size(980, 750, CoordinatesType.RATIO),
                dialogBean.text, "WriterFont", new Size(100, 0, CoordinatesType.RATIO), Color.WHITE, Text.TextAlign.LEFT, this);
        name = new Text("name-text", new Point(10, 10, CoordinatesType.RATIO), new Size(500, 100, CoordinatesType.RATIO),
                dialogBean.actor.name, "WriterFont", new Size(1000, 0, CoordinatesType.RATIO), dialogBean.nameColor, Text.TextAlign.LEFT, this);
        if (false) {
            imageActor = new Background("face", new Point(750, 0, CoordinatesType.RATIO), new Size(250, 250, CoordinatesType.RATIO),
                    new Filler(ActorResources.loadActorFace(dialogBean.actor.name)), this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    public void add(DialogBean dialogBean) {
        dialogBeans.add(dialogBean);
        if (dialogBeans.size() == 1) {
            try {
                reload();
            } catch (AppException e) {
                Writer.printStackTrace(logger, e);
            }
        }
    }

    public void add(Collection<DialogBean> dialogBeans) {
        this.dialogBeans.addAll(dialogBeans);
        if (this.dialogBeans.size() == dialogBeans.size()) {
            try {
                reload();
            } catch (AppException e) {
                Writer.printStackTrace(logger, e);
            }
        }
    }

    @Override
    public void recalculateChildes() {
        if (background != null) {
            background.recalculate();
            text.recalculate();
            name.recalculate();
            if (imageActor != null) {
                imageActor.recalculate();
            }
        }
    }
}
