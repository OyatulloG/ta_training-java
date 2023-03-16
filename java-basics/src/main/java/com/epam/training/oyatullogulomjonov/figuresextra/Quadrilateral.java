package com.epam.training.oyatullogulomjonov.figuresextra;

public class Quadrilateral extends Figure {
    private Point a;
    private Point b;
    private Point c;
    private Point d;
    
    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (!areVerticesNull(a, b, c, d) && isNonDegenerativeConvex(a, b, c, d)) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;            
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public Point centroid() {
        Segment segmentAC = new Segment(new Triangle(a, b, c).centroid(), new Triangle(a, d, c).centroid());
        Segment segmentBD = new Segment(new Triangle(a, b, d).centroid(), new Triangle(b, c, d).centroid());
        return segmentAC.intersection(segmentBD);
    } 
    
    
    @Override
    public boolean isTheSame(Figure figure) {
        return this.getClass() == figure.getClass() && this.equals(figure);
    }
 
    @Override
    public boolean equals(Object obj) {
        Quadrilateral other = (Quadrilateral) obj;
        return (a.equals(other.a) || a.equals(other.b) || a.equals(other.c) || a.equals(other.d)) && 
               (b.equals(other.a) || b.equals(other.b) || b.equals(other.c) || b.equals(other.d)) &&
               (c.equals(other.a) || c.equals(other.b) || c.equals(other.c) || c.equals(other.d)) &&
               (d.equals(other.a) || d.equals(other.b) || d.equals(other.c) || d.equals(other.d));
    }
        
    private boolean areVerticesNull(Point a, Point b, Point c, Point d) {
    	return a == null || b == null || c == null || d == null;
    }
    
    private boolean isNonDegenerativeConvex(Point a, Point b, Point c, Point d) {
        Point intersection = new Segment(a, c).intersection(new Segment(b, d));        
        
        if (intersection == null) {
            //the quadrilateral is not convex
            return false;
        }        
        
        //if these 4 non-degenerative triangles are created successfully, the quadrilateral is non-degenerative
        Triangle triangleABIntersection = new Triangle(a, b, intersection);
        Triangle triangleBCIntersection = new Triangle(b, c, intersection);
        Triangle triangleCDIntersection = new Triangle(c, d, intersection);
        Triangle triangleADIntersection = new Triangle(a, d, intersection);        
        return true;
    }
}
