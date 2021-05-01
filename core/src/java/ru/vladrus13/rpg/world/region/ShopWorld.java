package ru.vladrus13.rpg.world.region;


import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.*;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.factory.components.ButtonFactory;
import ru.vladrus13.jgraphic.factory.components.TextFactory;
import ru.vladrus13.rpg.basic.event.ShopEvent;
import ru.vladrus13.rpg.basic.event.region.RegionEventDrawing;
import ru.vladrus13.rpg.world.items.Item;
import ru.vladrus13.rpg.world.items.inventory.Items;
import ru.vladrus13.rpg.world.places.Barter;
import ru.vladrus13.rpg.world.places.BarterPlace;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShopWorld extends Frame {
    public final BarterPlace barterPlace;
    public final Background background = new Background("blue",
            new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO),
            new Filler(new Color(0, 0, 255, 225)), this);
    public final Region region;
    final ButtonFactory buttonFactory = new ButtonFactory()
            .setBackground(new Background("back",
                    new Point(0, 0, CoordinatesType.RATIO),
                    new Size(1000, 1000, CoordinatesType.RATIO),
                    new Filler(new Color(0, 0, 0, 5)), null))
            .setChooseBackground(new Background("backChoose",
                    new Point(0, 0, CoordinatesType.RATIO),
                    new Size(1000, 1000, CoordinatesType.RATIO),
                    new Filler(new Color(0, 0, 0, 25)), null));
    final Function<Items, String> fromItemType =
            itemType -> (itemType.count == 1 ? "" : itemType.count + " ") + itemType.item.name;
    final Function<Collection<Items>, String> fromCollectionItemTypes =
            itemTypes -> itemTypes.stream().map(fromItemType).collect(Collectors.joining(", "));
    private final TextFactory textFactory = new TextFactory()
            .setColor(Color.BLACK)
            .setTextAlign(Text.TextAlign.LEFT)
            .setFontSize(new Size(32, 0, CoordinatesType.REAL))
            .setNameFont("PixelFontGame");
    public Choose choose;
    public EmptyFrame barterIn;
    public EmptyFrame barterOut;
    public EmptyFrame out;

    public ShopWorld(String name, Region parent, BarterPlace barterPlace) {
        super(name, new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
        this.barterPlace = barterPlace;
        this.region = parent;
        reShop(null);
        addChild(choose);
        addChild(background);
        addChild(barterIn);
        addChild(barterOut);
        addChild(out);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        background.draw(graphics);
        choose.draw(graphics);
        barterIn.draw(graphics);
        barterOut.draw(graphics);
        out.draw(graphics);
    }

    private void reShop(Integer currentNumber) {
        int liveCount = (int) barterPlace.barters.stream().filter(barter -> barter.count > 0).count();
        Event[] eventsKey = new Event[liveCount];
        eventsKey = barterPlace.barters.stream()
                .filter(barter -> barter.count > 0)
                .map(ShopEvent::new)
                .collect(Collectors.toList())
                .toArray(eventsKey);
        Event[] eventsMouse = new Event[eventsKey.length];
        String[] names = new String[liveCount];
        names = barterPlace.barters.stream()
                .filter(barter -> barter.count > 0)
                .map(barter -> barter.count + " " + fromCollectionItemTypes.apply(barter.from))
                .collect(Collectors.toCollection(ArrayList::new))
                .toArray(names);
        Choose choose1;
        try {
            choose1 = Choose.getInstance("shop", liveCount,
                    new Point(50, 50, CoordinatesType.RATIO), new Size(500, 900, CoordinatesType.RATIO),
                    this, new Size(1000, 50, CoordinatesType.RATIO), names, eventsKey, eventsMouse, buttonFactory, textFactory);
        } catch (AppException e) {
            e.printStackTrace();
            choose1 = null;
        }
        choose = choose1;
        if (currentNumber != null) {
            choose.current = currentNumber;
        }
        redrawBarter();
    }

    private void redrawBarter() {
        barterIn = new EmptyFrame("inB",
                new Point(600, 50, CoordinatesType.RATIO),
                new Size(350, 240, CoordinatesType.RATIO), this);
        barterOut = new EmptyFrame("outB",
                new Point(600, 250, CoordinatesType.RATIO),
                new Size(350, 240, CoordinatesType.RATIO), this);
        out = new EmptyFrame("out",
                new Point(600, 500, CoordinatesType.RATIO),
                new Size(350, 450, CoordinatesType.RATIO), this);
        Barter current = barterPlace.barters.get(choose.current);
        addTexts(barterIn, current.from, textFactory);
        addTexts(barterOut, current.to, textFactory);
        if (current.to.size() == 1) {
            Item item = current.to.get(0).item;
            try {
                Text name = textFactory.getInstance("name", item.name, out);
                Text description = textFactory.getInstance("desc", item.description, out);
                name.setFrame(new Point(50, 50, CoordinatesType.RATIO),
                        new Size(900, 100, CoordinatesType.RATIO));
                description.setFrame(new Point(50, 160, CoordinatesType.RATIO),
                        new Size(900, 800, CoordinatesType.RATIO));
                out.addChild(name);
                out.addChild(description);
            } catch (AppException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTexts(EmptyFrame emptyFrame, ArrayList<Items> items, TextFactory textFactory) {
        long[] distribution = distribution(Math.max(items.size(), 3));
        for (int i = 0; i < items.size(); i++) {
            Items itemType = items.get(i);
            try {
                Text text = textFactory.getInstance("button" + i, fromItemType.apply(itemType), emptyFrame);
                text.setFrame(
                        new Point(50, distribution[i], CoordinatesType.RATIO),
                        new Size(900, distribution[1] - distribution[0], CoordinatesType.RATIO));
                emptyFrame.addChild(text);
            } catch (AppException e) {
                e.printStackTrace();
            }
        }
    }

    private long[] distribution(int count) {
        long[] answer = new long[count];
        long one = 1000 / count;
        long current = 0;
        for (int i = 0; i < count; i++) {
            answer[i] = current;
            current += one;
        }
        return answer;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_ENTER) {
            choose.keyPressed(keyEvent);
            redrawBarter();
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            callEvent(new RegionEventDrawing(this, true, true, true));
        }
    }

    @Override
    public void callEvent(Event event) {
        if (event instanceof ShopEvent) {
            Barter barter = ((ShopEvent) event).barter;
            if (barter.count > 0) {
                for (Items items : barter.from) {
                    Items founded = region.hero.inventory.find(items.item);
                    if (founded == null || founded.count < items.count) {
                        return;
                    }
                }
                for (Items items : barter.from) {
                    region.hero.inventory.removeItems(items);
                }
                for (Items items : barter.to) {
                    region.hero.inventory.addItems(items);
                }
                barter.count--;
            }
            reShop(choose.current);
            return;
        }
        super.callEvent(event);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }
}
