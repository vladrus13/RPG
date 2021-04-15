package ru.vladrus13.rpg.basic.direction;

import org.json.JSONObject;
import ru.vladrus13.rpg.saves.Savable;

/**
 * Direction enum
 */
public enum Direction implements Savable {
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


    @Override
    public String toSaveString() {
        return this.getCapitalize();
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject(this.getCapitalize());
    }
}
