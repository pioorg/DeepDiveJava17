package org.przybyl.ddj17.sealing.geometry;

abstract sealed class Shape {


    Shape rotate(Shape shape, double angle) {
        if (shape instanceof Circle) return shape;
        else if (shape instanceof Rectangle) return shape.rotate(angle);
        else if (shape instanceof Square) return shape.rotate(angle);
        // no else needed!
        //or is it?
        return null;
    }

    private Shape rotate(double angle) {
        return null;
    }
}

final class Circle extends Shape {
}

final class Rectangle extends Shape {
}

final class Square extends Shape {
}


