package com.epam.training.oyatullogulomjonov.lineintersection;

public class Line {
    //y = k * x + b		
    private int k;
    private int b;
    
    public Line (int k, int b) {
        this.k = k;
        this.b = b;
    }

    public Point intersection (Line otherLine) {        
        Point intersection = null;
        if (this.k != otherLine.k) {
            int x = (otherLine.b - this.b) / (this.k - otherLine.k);
            int y = this.k * x + this.b;            
            intersection = new Point(x, y);
        }       
        return intersection;
    }
}
