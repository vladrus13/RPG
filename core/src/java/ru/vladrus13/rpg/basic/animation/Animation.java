package ru.vladrus13.rpg.basic.animation;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.basic.event.animation.AnimationEventEnd;
import ru.vladrus13.rpg.basic.event.animation.AnimationEventFrameEnd;
import ru.vladrus13.rpg.resources.ImageTable;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Animation extends UpdatedFrame {

    private final ArrayList<AnimationFrame> animationFrameArrayList;
    private int current = 0;

    public Animation(String name, Point start, Size size, Frame parent, ArrayList<AnimationFrame> animationFrameArrayList) {
        super(name, start, size, parent);
        this.animationFrameArrayList = animationFrameArrayList;
    }

    public Animation(String name, Point start, Size size, Frame parent, ArrayList<Filler> fillers, int coolDown) {
        super(name, start, size, parent);
        animationFrameArrayList = new ArrayList<>();
        for (int i = 0; i < fillers.size(); i++) {
            animationFrameArrayList.add(
                    new AnimationFrame("animation" + i, start.copy(), size.copy(), this, fillers.get(i), coolDown));
        }
    }

    public Animation(String name, Point start, Size size, Frame parent, ImageTable imageTable, int coolDown) {
        this(name, start, size, parent,
                (ArrayList<Filler>) imageTable
                        .getAll().stream().map(Filler::new)
                        .collect(Collectors.toCollection(ArrayList::new)), coolDown);
    }

    @Override
    public void callEvent(Event event) {
        if (event instanceof AnimationEventFrameEnd) {
            current++;
            if (current >= animationFrameArrayList.size()) {
                callEvent(new AnimationEventEnd());
            }
        }
        super.callEvent(event);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        if (current < animationFrameArrayList.size()) {
            animationFrameArrayList.get(current).update(delay);
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (current < animationFrameArrayList.size()) {
            animationFrameArrayList.get(current).draw(graphics);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
