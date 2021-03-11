package ru.vladrus13.game.basic.direction;

public enum Direction {
    UP {
        @Override
        public String getCapitalize() {
            return "Up";
        }
    }, DOWN {
        @Override
        public String getCapitalize() {
            return "Down";
        }
    }, LEFT {
        @Override
        public String getCapitalize() {
            return "Left";
        }
    }, RIGHT {
        @Override
        public String getCapitalize() {
            return "Right";
        }
    };


    public abstract String getCapitalize();
}
