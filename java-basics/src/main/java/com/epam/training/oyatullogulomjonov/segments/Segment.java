package com.epam.training.oyatullogulomjonov.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
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

    double length() {
        return sqrt(pow((end.getY()-start.getY()), 2) + pow((end.getX()-start.getX()), 2));
    }

    Point middle() {        
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;        
        return new Point(x, y);
    }

    Point intersection(Segment another) {
        //segment this((x1,y1), (x2,y2))
        //segment another((x3,y3), (x4,y4))
        Point intersection = null;
        double deltaX12 = this.start.getX() - this.end.getX();
        double deltaX13 = this.start.getX() - another.start.getX();
        double deltaX34 = another.start.getX() - another.end.getX();
        double deltaY12 = this.start.getY() - this.end.getY();
        double deltaY13 = this.start.getY() - another.start.getY();
        double deltaY34 = another.start.getY() - another.end.getY();        
        double t = (deltaX13 * deltaY34 - deltaY13 * deltaX34) / (deltaX12 * deltaY34 - deltaY12 * deltaX34);
        double u = (deltaX13 * deltaY12 - deltaY13 * deltaX12) / (deltaX12 * deltaY34 - deltaY12 * deltaX34);               
        if ((t >= 0 && t <= 1) && (u >= 0 && u <= 1)) {
            double x = this.start.getX() + t * (-deltaX12);
            double y = this.start.getY() + t * (-deltaY12);            
            intersection = new Point(x, y);
        }
        return intersection;
    }
}
