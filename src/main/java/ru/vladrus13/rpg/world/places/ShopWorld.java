package ru.vladrus13.rpg.world.places;


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
    public final Choose choose;

    Function<ItemType, String> fromItemType =
            itemType -> (itemType.count == 1 ? "" : itemType.count + " ") + itemType.item.name;

    Function<Collection<ItemType>, String> fromCollectionItemTypes =
            itemTypes -> itemTypes.stream().map(fromItemType).collect(Collectors.joining(", "));

    public ShopWorld(String name, Frame parent, Shop shop) {
        super(name, new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
        this.shop = shop;
        ButtonFactory buttonFactory = new ButtonFactory()
                .setBackground(new Background("back", new Filler(new Color(0, 0, 0, 255)), null))
                .setChooseBackground(new Background("backChoose", new Filler(new Color(0, 0, 0, 250)), null));
        TextFactory textFactory = new TextFactory()
                .setColor(Color.BLACK)
                .setTextAlign(Text.TextAlign.LEFT)
                .setFontSize(new Size(800, 0, CoordinatesType.RATIO))
                .setNameFont("PixedFontGame");
        Event[] eventsKey = new Event[shop.barters.size()];
        Event[] eventsMouse = new Event[shop.barters.size()];
        String[] names = new String[shop.barters.size()];
        names = shop.barters.stream()
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
        addChild(choose);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        choose.draw(graphics);

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }
}
