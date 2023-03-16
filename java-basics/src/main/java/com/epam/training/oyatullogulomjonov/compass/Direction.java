package com.epam.training.oyatullogulomjonov.compass;

import java.util.Optional;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);
    private int degrees;
    
    Direction(final int degrees) {
        this.degrees = degrees;
    }

    public static Direction ofDegrees(int degrees) {
        degrees = (degrees >= 0) ? (degrees % 360) : (degrees % 360 + 360);        
        for (Direction direction : Direction.values()) {
            if (direction.ordinal() * 45 == degrees) {
                return direction;
            }
        }
        return null;
    }

    public static Direction closestToDegrees(int degrees) {
        degrees = (degrees >= 0) ? (degrees % 360) : (degrees % 360 + 360);
        Direction closestDirection = Direction.N;        
        for (Direction direction : Direction.values()) {
            if (Math.abs(direction.ordinal() * 45 - degrees) < 23) {
                closestDirection = direction;
                break;
            }
        }
        return closestDirection;
    }

    public Direction opposite() {
        int degrees = Direction.this.ordinal() * 45;
        Direction oppositeDirection = Direction.N;        
        for (Direction direction : Direction.values()) {
            if (direction.ordinal() * 45 == (degrees + 180) % 360) {
                oppositeDirection = direction;
                break;
            }
        }
        return oppositeDirection;
    }

    public int differenceDegreesTo(Direction direction) {
        int difference = Math.abs(this.ordinal() - direction.ordinal()) * 45;
        return (difference > 180) ? (360 - difference) : difference;
    }
}
