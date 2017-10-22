package ru.stqa.pft.sanbox;

public class TaskPoint {
    public static void main(String[] args) {

        Point p1 = new Point(0,0);
        Point p2 = new Point(1,1);
        System.out.println("Расстояние между двумя точками distance(p1,p2) = " + distance(p1, p2));
        System.out.println("Расстояние между двумя точками p1.distance(p2)  = " + p1.distance(p2));

    }


    public static double distance(Point p1, Point p2) {
        return Math.sqrt( Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2) );
    }
}
