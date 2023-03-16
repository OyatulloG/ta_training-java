package com.epam.training.oyatullogulomjonov.figuresextra;

public class Triangle extends Figure {
    private Point a;
    private Point b;
    private Point c;
    
    public Triangle(Point a, Point b, Point c) {
        if (!areVerticesNull(a,b,c) && !a.equals(b) && !b.equals(c) && !a.equals(c) && !isCollinear(a,b,c)) {
            this.a = a;
            this.b = b;
            this.c = c;
        } else {
            throw new IllegalArgumentException();
        }
        
    }
    
    @Override
    public Point centroid() {
        double x = (a.getX() + b.getX() + c.getX()) / 3;
        double y = (a.getY() + b.getY() + c.getY()) / 3;
        return new Point(x, y);
    }

    @Override    
    public boolean isTheSame(Figure figure) {
        return this.getClass() == figure.getClass() && this.equals(figure);
    }

    @Override
    public boolean equals(Object obj) {
        Triangle other = (Triangle) obj;
        return (a.equals(other.a) || a.equals(other.b) || a.equals(other.c)) && 
               (b.equals(other.a) || b.equals(other.b) || b.equals(other.c)) &&
               (c.equals(other.a) || c.equals(other.b) || c.equals(other.c));
    }

    private boolean isCollinear(Point a, Point b, Point c) {
        double deltaYab = a.getY() - b.getY();
        double deltaYbc = b.getY() - c.getY();
        double deltaYca = c.getY() - a.getY();        
        return a.getX() * deltaYbc + b.getX() * deltaYca + c.getX() * deltaYab == 0;
    }
                
    private boolean areVerticesNull(Point a, Point b, Point c) {
    	return a == null || b == null || c == null;
    }    
}
