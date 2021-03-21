package ru.vladrus13.game.basic.direction;

/**
 * Direction enum
 */
public enum Direction {
    /**
     * Up
     */
    UP {
        @Override
        public String getCapitalize() {
            return "Up";
        }
    },
    /**
     * Down
     */
    DOWN {
        @Override
        public String getCapitalize() {
            return "Down";
        }
    },
    /**
     * Left
     */
    LEFT {
        @Override
        public String getCapitalize() {
            return "Left";
        }
    },
    /**
     * Right
     */
    RIGHT {
        @Override
        public String getCapitalize() {
            return "Right";
        }
    };


    /**
     * @return capitalization string of direction
     */
    public abstract String getCapitalize();
}
