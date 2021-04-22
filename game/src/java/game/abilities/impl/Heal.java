package game.abilities.impl;

import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.basic.animation.Animation;
import ru.vladrus13.rpg.basic.event.region.RegionEventDrawing;
import ru.vladrus13.rpg.resources.ImageGameLoader;
import ru.vladrus13.rpg.world.actors.Ability;
import ru.vladrus13.rpg.world.actors.AbilitySelf;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.region.Region;

import java.nio.file.Path;

public class Heal extends AbilitySelf {

    public Heal() {
        id = 2;
        coolDown = 5000;
    }

    public static Ability getInstance() {
        return new Heal();
    }

    @Override
    public String getName() {
        return "Heal";
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public void activate(Actor from, Region region) {
        if (!from.isSpecialAbitily(this)) {
            from.realStatus.hp += 30;
            if (from.realStatus.hp > from.realStatus.maxHP) {
                from.realStatus.hp = from.realStatus.maxHP;
            }
        }
        Path path = Path.of("world").resolve("animations").resolve("Heal.png");
        ImageGameLoader.upload(path, new Size(192, 192, CoordinatesType.REAL));
        Animation animation = new Animation("heal", from.getStart().incX(-10).incY(-10), new Size(52, 52, CoordinatesType.REAL), region, ImageGameLoader.load(path), 100);
        region.callEvent(new RegionEventDrawing(animation, true, false, false));
    }
}
