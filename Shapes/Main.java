package Shapes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static java.lang.Math.PI;

public class Main {
    public class Circle implements IShape {
        public double radius;

        public Circle(double rad) {
            radius = rad;
        }

        @Override
        public double getPerimeter() {
            return 2 * PI * radius;
        }
    }

    public class Rectangle implements IShape {
        public double a;
        public double b;

        public Rectangle(double newA, double newB) {
            a = newA;
            b = newB;
        }

        @Override
        public double getPerimeter() {
            return (a + b) * 2;
        }
    }

    public class Triangle implements IShape {
        public double a;
        public double b;
        public double c;

        public Triangle(double newA, double newB, double newC) {
            a = newA;
            b = newB;
            c = newC;
        }

        @Override
        public double getPerimeter() {
            return a + b + c;
        }
    }

    public void testFunc() {
        Random random = new Random();
        ArrayList<IShape> shapes = new ArrayList<IShape>(10);

        for (int i = 0; i < 10; i++) {
            int shapeType = random.nextInt(3);

            switch (shapeType) {
                case 0:
                    shapes.add(new Circle(random.nextDouble() * 50));
                    break;
                case 1:
                    shapes.add(new Rectangle(random.nextDouble() * 50, random.nextDouble() * 50));
                    break;
                case 2:
                    shapes.add(new Triangle(random.nextDouble() * 50, random.nextDouble() * 50, random.nextDouble() * 50));
                    break;
            }
        }

        IShape shapeWithMaxPerimeter = shapes.stream().max(Comparator.comparing(shape -> shape.getPerimeter())).orElse(null);
        IShape shapeWithMinPerimeter = shapes.stream().min(Comparator.comparing(shape -> shape.getPerimeter())).orElse(null);

        for(IShape s: shapes) {
            System.out.println(s.getPerimeter());
        }

        System.out.println("Shape with max perimeter - shape class: " + shapeWithMaxPerimeter.getClass() + ", perimeter: " + shapeWithMaxPerimeter.getPerimeter());
        System.out.println("Shape with min perimeter - shape class: " + shapeWithMinPerimeter.getClass() + ", perimeter: " + shapeWithMinPerimeter.getPerimeter());
    }

    public static void main(String[] args) {
        Main t = new Main();
        t.testFunc();
    }
}


;
