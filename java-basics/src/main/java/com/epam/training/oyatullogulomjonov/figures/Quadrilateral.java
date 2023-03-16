package com.epam.training.oyatullogulomjonov.figures;

class Quadrilateral extends Figure {
    private Point a;
    private Point b;
    private Point c;
    private Point d;
    
    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    @Override
    public double area() {
        return new Triangle(a, b, c).area() + new Triangle(a, d, c).area();
    }
    
    @Override
    public String pointsToString() {
        return a.toString() + b.toString() + c.toString() + d.toString();
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    public Point leftmostPoint() {
        Point[] points = { a, b, c, d };
        double min = a.getX();
        Point leftMostPoint = a;        
        for (Point point : points) {
            if (min > point.getX()) {
                min = point.getX();
                leftMostPoint = point;
            }
        }
        return leftMostPoint;
    }    
}
