package ru.vladrus13.rpg.world.actors;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.ai.world.command.WarZoneAI;
import ru.vladrus13.rpg.ai.world.command.AttackCommand;
import ru.vladrus13.rpg.ai.world.command.Command;
import ru.vladrus13.rpg.ai.world.command.StepCommand;
import ru.vladrus13.rpg.ai.world.command.WaitCommand;
import ru.vladrus13.rpg.world.region.WarZone;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Enemy extends Actor {

    public long time = 0;
    private WarZoneAI warZoneAI;

    public Enemy(int id, String systemName, Point start, String name, WarZone region) {
        super(id, systemName, start, name, region);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        int pixelsHP = tileSize * realStatus.hp / realStatus.maxHP;
        graphics.setColor(new Color(100, 0, 0));
        graphics.fillRect(start.x, start.y, tileSize, tileSize / 4);
        graphics.setColor(new Color(255, 0, 0));
        graphics.fillRect(start.x, start.y, pixelsHP, tileSize / 4);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    public void setWarZoneAI(WarZoneAI warZoneAI) {
        this.warZoneAI = warZoneAI;
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        super.nonCheckingUpdate(delay);
        time -= delay;
        if (time <= 0 && walkDirection == null) {
            onFree();
        }
    }

    public void onFree() {
        time = 0;
        Command command = warZoneAI.getCommand((WarZone) region, this);
        if (command instanceof AttackCommand) {
            time += 1000;
            if (((AttackCommand) command).ability instanceof AbilityActor) {
                ((AbilityActor) ((AttackCommand) command).ability).activate(this, ((AttackCommand) command).to, region);
            }
            // TODO another type of abilities
            return;
        }
        if (command instanceof WaitCommand) {
            time += ((WaitCommand) command).time;
            return;
        }
        if (command instanceof StepCommand) {
            makeMove(((StepCommand) command).move);
            return;
        }
        throw new IllegalArgumentException("Unknown command");
    }

    @Override
    public void onStep() {
        onFree();
    }
}
