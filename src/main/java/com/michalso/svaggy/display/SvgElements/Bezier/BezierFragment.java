package com.michalso.svaggy.display.SvgElements.Bezier;


import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/*
for c first two points are control points, last one is end coordinate
 */
public class BezierFragment {

    public enum Type {
        C, M, L, Z
    }

    private String type;
    private List<Point2D> points = new ArrayList<>();

    public BezierFragment(String type) {
        this.type = type;
        this.points = points;
    }

    public BezierFragment(BezierFragment frag) {
        this.type = frag.type;
        points = new ArrayList<>();
        frag.points.forEach(p -> points.add(new Point2D.Double(p.getX(), p.getY())));
    }

    public BezierFragment(String type, List<Point2D> points) {
        this.type = type;
        this.points = points;
    }

    public String getString() {
        String valString = type;
        for (Point2D point : points) {
            valString += " " + String.valueOf(point.getX()) +"," + String.valueOf(point.getY());
        }

        return valString;
    }


    //point index realtes to 3 values(2 control and one position)
    public void changeControlPoints(int pointIndex, Point2D offsetP1, Point2D offsetP2) {
        if (!type.toUpperCase().equals("C")) {
            throw new RuntimeException("Change control points is only available for Type C");
        }

        int realIndex = pointIndex * 3;

        Point2D contrPoint1 = points.get(realIndex);
        Point2D contrPoint2 = points.get(realIndex + 1);

        contrPoint1.setLocation(contrPoint1.getX() + offsetP1.getX(), contrPoint1.getY() + offsetP1.getY());
        contrPoint2.setLocation(contrPoint2.getX() + offsetP2.getX(), contrPoint2.getY() + offsetP2.getY());
    }

    public void changeAllControlPoints(Point2D offsetP1, Point2D offsetP2) {

        for (int i=0; i<points.size(); i+= 3) {
            Point2D contrPoint1 = points.get(i);
            Point2D contrPoint2 = points.get(i + 1);

            contrPoint1.setLocation(contrPoint1.getX() + offsetP1.getX(), contrPoint1.getY() + offsetP1.getY());
            contrPoint2.setLocation(contrPoint2.getX() + offsetP2.getX(), contrPoint2.getY() + offsetP2.getY());
        }
    }

    public void addPoint(Point2D point) {
        this.points.add(point);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public void setPoints(List<Point2D> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return type + String.valueOf(points);
    }
}
