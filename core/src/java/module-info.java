module ru.vladrus13.rpg {
    exports ru.vladrus13.rpg;
    exports ru.vladrus13.rpg.menu;
    exports ru.vladrus13.rpg.world;
    exports ru.vladrus13.rpg.basic.event.world;
    exports ru.vladrus13.rpg.world.region;
    exports ru.vladrus13.rpg.basic.direction;
    exports ru.vladrus13.rpg.basic.event.region;
    exports ru.vladrus13.rpg.world.actors;
    exports ru.vladrus13.rpg.world.actors.region;
    exports ru.vladrus13.rpg.world.components;
    exports ru.vladrus13.rpg.world.items.inventory;
    exports ru.vladrus13.rpg.world.places;
    exports ru.vladrus13.rpg.world.items;
    exports ru.vladrus13.rpg.resources;
    exports ru.vladrus13.rpg.saves;
    exports ru.vladrus13.rpg.world.factory;
    exports ru.vladrus13.rpg.ai.world.command;
    exports ru.vladrus13.rpg.world.quickmenu;
    exports ru.vladrus13.rpg.dialog;

    requires transitive java.logging;
    requires transitive ru.vladrus13.jgraphic;
    requires java.desktop;
    requires org.json;
}