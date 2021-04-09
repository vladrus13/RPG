package ru.vladrus13.rpg.world.places;


import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.*;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.factory.components.ButtonFactory;
import ru.vladrus13.jgraphic.factory.components.TextFactory;
import ru.vladrus13.rpg.basic.event.ShopEvent;
import ru.vladrus13.rpg.world.items.Item;
import ru.vladrus13.rpg.world.items.inventory.ItemType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShopWorld extends Frame {
    public final Shop shop;
    public Choose choose;
    public final Background background = new Background("blue",
            new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO),
            new Filler(new Color(0, 0, 255, 127)), this);
    public EmptyFrame barterIn;
    public EmptyFrame barterOut;
    public EmptyFrame out;

    Function<ItemType, String> fromItemType =
            itemType -> (itemType.count == 1 ? "" : itemType.count + " ") + itemType.item.name;

    Function<Collection<ItemType>, String> fromCollectionItemTypes =
            itemTypes -> itemTypes.stream().map(fromItemType).collect(Collectors.joining(", "));

    public ShopWorld(String name, Frame parent, Shop shop) {
        super(name, new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
        this.shop = shop;
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
        ButtonFactory buttonFactory = new ButtonFactory()
                .setBackground(new Background("back",
                        new Point(0, 0, CoordinatesType.RATIO),
                        new Size(1000, 1000, CoordinatesType.RATIO),
                        new Filler(new Color(0, 0, 0, 5)), null))
                .setChooseBackground(new Background("backChoose",
                        new Point(0, 0, CoordinatesType.RATIO),
                        new Size(1000, 1000, CoordinatesType.RATIO),
                        new Filler(new Color(0, 0, 0, 25)), null));
        TextFactory textFactory = new TextFactory()
                .setColor(Color.BLACK)
                .setTextAlign(Text.TextAlign.LEFT)
                .setFontSize(new Size(32, 0, CoordinatesType.REAL))
                .setNameFont("PixelFontGame");
        Event[] eventsKey = new Event[shop.barters.size()];
        eventsKey = shop.barters.stream()
                .filter(barter -> barter.count > 0)
                .map(ShopEvent::new)
                .collect(Collectors.toList())
                .toArray(eventsKey);
        Event[] eventsMouse = new Event[eventsKey.length];
        String[] names = new String[shop.barters.size()];
        names = shop.barters.stream()
                .filter(barter -> barter.count > 0)
                .map(barter -> fromCollectionItemTypes.apply(barter.from))
                .collect(Collectors.toCollection(ArrayList::new))
                .toArray(names);
        Choose choose1;
        try {
            choose1 = Choose.getInstance("shop", shop.barters.size(),
                    new Point(50, 50, CoordinatesType.RATIO), new Size(500, 900, CoordinatesType.RATIO),
                    this, new Size(1000, 50, CoordinatesType.RATIO), names, eventsKey, eventsMouse, buttonFactory, textFactory);
        } catch (GameException e) {
            e.printStackTrace();
            choose1 = null;
        }
        choose = choose1;
        if (currentNumber != null) {
            choose.current = currentNumber;
        }
        barterIn = new EmptyFrame("inB",
                new Point(600, 50, CoordinatesType.RATIO),
                new Size(350, 240, CoordinatesType.RATIO), this);
        barterOut = new EmptyFrame("outB",
                new Point(600, 250, CoordinatesType.RATIO),
                new Size(350, 240, CoordinatesType.RATIO), this);
        out = new EmptyFrame("out",
                new Point(600, 500, CoordinatesType.RATIO),
                new Size(350, 450, CoordinatesType.RATIO), this);
        Barter current = shop.barters.get(choose.current);
        addTexts(barterIn, current.from, textFactory);
        addTexts(barterOut, current.to, textFactory);
        if (current.to.size() == 1) {
            Item item = current.to.get(0).item;
            try {
                Text name = textFactory.getInstance("name", item.name, out);
                Text description = textFactory.getInstance("desc", item.description, out);
                name.setFrame(new Size(300, 100, CoordinatesType.RATIO),
                        new Point(50, 50, CoordinatesType.RATIO));
                description.setFrame(new Size(600, 800, CoordinatesType.RATIO),
                        new Point(360, 160, CoordinatesType.RATIO));
                out.addChild(name);
                out.addChild(description);
            } catch (GameException e) {
                e.printStackTrace();
            }

        }
    }

    private void addTexts(EmptyFrame emptyFrame, ArrayList<ItemType> items, TextFactory textFactory) {
        long[] distribution = distribution(Math.max(items.size(), 3), 1000);
        for (int i = 0; i < items.size(); i++) {
            ItemType itemType = items.get(i);
            try {
                Text text = textFactory.getInstance("b", fromItemType.apply(itemType), emptyFrame);
                text.setFrame(
                        new Size(350, distribution[1] - distribution[0], CoordinatesType.RATIO),
                        new Point(600, distribution[i], CoordinatesType.RATIO));
                emptyFrame.addChild(text);
            } catch (GameException e) {
                e.printStackTrace();
            }
        }
    }

    private long[] distribution(int count, int size) {
        long[] answer = new long[count];
        long one = size / count;
        long current = 0;
        for (int i = 0; i < count; i++) {
            answer[i] = current;
            current += one;
        }
        return answer;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }
}
