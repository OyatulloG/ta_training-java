package com.epam.training.oyatullogulomjonov.figuresextra;

public class Circle extends Figure {
    private Point center;
    private double radius;
    private double errorDelta = 0.000001d;
    
    public Circle(Point center, double radius) {
        if (center != null && radius > 0) {
            this.center = center;
            this.radius = radius;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public Point centroid() {
        return center;
    }
    
    @Override
    public boolean isTheSame(Figure figure) {        
        return this.getClass() == figure.getClass() && this.equals(figure);
    }
    
    @Override
    public boolean equals(Object obj) {
        Circle other = (Circle) obj;
        return this.center.equals(other.center) && Math.abs(this.radius - other.radius) < errorDelta;
    }
}
