package com.epam.training.oyatullogulomjonov.figuresextra;

class Point {
    private double x;
    private double y;
    private double errorDelta = 0.000001d;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object obj) {
        Point other = (Point) obj;     
        return Math.abs(this.x - other.x) < errorDelta && Math.abs(this.y - other.y) < errorDelta; 
    }    
}
