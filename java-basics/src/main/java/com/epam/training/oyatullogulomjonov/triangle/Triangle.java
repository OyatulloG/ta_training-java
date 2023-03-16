package com.epam.training.oyatullogulomjonov.triangle;

class Triangle {
    private Point a;
    private Point b;
    private Point c;
    
    public Triangle(Point a, Point b, Point c) {
        double slopeAB = (b.getY() - a.getY()) / (b.getX() - a.getX());
        double slopeAC = (c.getY() - a.getY()) / (c.getX() - a.getX());
        double slopeBC = (c.getY() - b.getY()) / (c.getX() - b.getX());
        
        if (slopeAB != slopeAC && slopeAB != slopeBC && slopeAC != slopeBC) {
            this.a = a;
            this.b = b;
            this.c = c;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double area() {
        Segment sideAB = new Segment(a, b);
        Segment sideAC = new Segment(a, c);
        Segment sideBC = new Segment(b, c);
        double halfPerimeter = (sideAB.length() + sideAC.length() + sideBC.length()) / 2;        
        return Math.sqrt(halfPerimeter * (halfPerimeter-sideAB.length()) * (halfPerimeter-sideAC.length()) * (halfPerimeter-sideBC.length()));
    }

    public Point centroid(){
        double x = (a.getX() + b.getX() + c.getX()) / 3;
        double y = (a.getY() + b.getY() + c.getY()) / 3;        
        return new Point(x, y);
    }
}
