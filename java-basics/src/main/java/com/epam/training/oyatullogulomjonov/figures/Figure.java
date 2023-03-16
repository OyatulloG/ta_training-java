package com.epam.training.oyatullogulomjonov.figures;

abstract class Figure{
    public abstract double area();

    public abstract String pointsToString();

    public abstract Point leftmostPoint();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + pointsToString() + "]";
    }    
}
