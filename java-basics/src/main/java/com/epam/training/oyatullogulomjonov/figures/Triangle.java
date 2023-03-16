package com.epam.training.oyatullogulomjonov.figures;

class Triangle extends Figure {
    private Point a;
    private Point b;
    private Point c;
    
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public double area() {
        double sideAB = getSideLength(a, b);
        double sideBC = getSideLength(b, c);
        double sideAC = getSideLength(a, c);
        double halfPerimeter = (sideAB + sideBC + sideAC) / 2;        
        return Math.sqrt((halfPerimeter - sideAB) * (halfPerimeter - sideBC) * (halfPerimeter - sideAC) * halfPerimeter);       
    }
    
    @Override
    public String pointsToString() {
        return a.toString() + b.toString() + c.toString();
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    public Point leftmostPoint() {
        Point[] points = { a, b, c };
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

    private double getSideLength(Point start, Point end) {
        return Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(),2));
    }    
}
