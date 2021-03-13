package ru.vladrus13.game.basic.event.returned;

public class ReturnInt extends ReturnEvent {
    public static final int NOTHING = 0;
    public static final int PRESSED = 1;
    public static final int TO_MENU = 101;
    public static final int TO_WORLD = 102;
    public static final int END_GAME = 103;

    private final int event;

    public ReturnInt(int event) {
        this.event = event;
    }

    public int getEvent() {
        return event;
    }
}
