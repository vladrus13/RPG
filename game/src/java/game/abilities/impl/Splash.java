package game.abilities.impl;

import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.basic.animation.Animation;
import ru.vladrus13.rpg.basic.event.region.RegionEventFocused;
import ru.vladrus13.rpg.resources.ImageGameLoader;
import ru.vladrus13.rpg.world.actors.Ability;
import ru.vladrus13.rpg.world.actors.AbilityActor;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.nio.file.Path;

public class Splash extends AbilityActor {
    @Override
    public void activate(Actor from, Actor to, Region region) {
        if (!to.isSpecialAbitily(this)) {
            to.onPhysical(from.realStatus.attack);
        }
        Path path = Path.of("world").resolve("animations").resolve("Splash.png");
        ImageGameLoader.upload(path, new Size(192, 192, CoordinatesType.REAL));
        Animation animation = new Animation("heal", from.getStart().copy(), from.getSize().copy(), region, ImageGameLoader.load(path), 10);
        region.callEvent(new RegionEventFocused(animation, true, false));
    }

    @Override
    public String getName() {
        return "Splash";
    }

    @Override
    public int getId() {
        return 1;
    }

    public static Ability getInstance() {
        return new Splash();
    }
}
