package com.epam.training.oyatullogulomjonov.triangle;

import static java.lang.StrictMath.pow;

class Segment {
    private Point start;
    private Point end;
    
    public Segment(Point start, Point end) {
        if (start != null && end != null && !start.equals(end)) {
            this.start = start;
            this.end = end;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public double length() {
        return Math.sqrt(pow((start.getX()-end.getX()), 2) + pow((start.getY()-end.getY()), 2));
    }
}

