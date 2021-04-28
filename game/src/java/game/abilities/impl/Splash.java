package game.abilities.impl;

import ru.vladrus13.jgraphic.basic.animation.Animation;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.resources.ImageTableLoader;
import ru.vladrus13.rpg.basic.event.region.RegionEventDrawing;
import ru.vladrus13.rpg.world.actors.Ability;
import ru.vladrus13.rpg.world.actors.AbilityActor;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.nio.file.Path;

public class Splash extends AbilityActor {

    public Splash() {
        id = 1;
        coolDown = 400;
        loadIcon();
    }

    public static Ability getInstance() {
        return new Splash();
    }

    @Override
    public void activate(Actor from, Actor to, Region region) {
        if (isNotUsable()) {
            return;
        }
        if (!to.isSpecialAbitily(this)) {
            to.onPhysical(from.realStatus.attack);
        }
        Path path = Path.of("world").resolve("animations").resolve("Splash.png");
        ImageTableLoader.upload(path, new Size(192, 192, CoordinatesType.REAL));
        Animation animation = new Animation("heal", to.getStart().copy(), to.getSize().copy(), region, ImageTableLoader.load(path), 100);
        region.callEvent(new RegionEventDrawing(animation, true, false, false));
    }

    @Override
    public String getName() {
        return "Splash";
    }

    @Override
    public int getId() {
        return 1;
    }
}
