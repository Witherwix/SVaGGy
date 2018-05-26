package com.michalso.svaggy.display.SvgElements.Bezier;


import com.michalso.svaggy.display.SvgElements.Basic.Boundable;
import com.michalso.svaggy.display.SvgElements.Basic.BoundingBox;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
for c first two points are control points, last one is end coordinate
 */
public class BezierFragment {
    public enum Type {
        C, M, L, Z
    }

    private String type;
    private List<Point2D> points = new ArrayList<>();

    /*
    algorithm and implementation taken from:http://jsfiddle.net/SalixAlba/QQnvm/4/
     */
    public Optional<BoundingBox> getBoundingBox(Point2D prevPos) {
        if (type.toUpperCase().equals("C")) {
            return Optional.of(getBoundingBoxForC(prevPos));
        }
        else if (type.toUpperCase().equals("L"))
        {
            return Optional.of(getBoundingBoxForL(prevPos));
        }

        return Optional.empty();
    }

    //todo move types to seperate classes
    public BoundingBox getBoundingBoxForC(Point2D prevPos) {

        prevPos.clone();
        List<BoundingBox> boxes = new ArrayList<>();
        for(int i=0; i<points.size(); i+= 3) {
            List<Double> xPoints = getAbsolutePointsRelativeToStartPos(prevPos).stream().map(e -> e.getX()).collect(Collectors.toList());
            List<Double> yPoints = getAbsolutePointsRelativeToStartPos(prevPos).stream().map(e -> e.getY()).collect(Collectors.toList());
            xPoints.add(0, prevPos.getX());
            yPoints.add(0, prevPos.getY());

            Point2D minMaxX = getLhCoord(xPoints);
            Point2D minMaxY = getLhCoord(yPoints);

            boxes.add(new BoundingBox(minMaxX.getX(), minMaxX.getY(), minMaxY.getX(), minMaxY.getY()));
            prevPos.setLocation(prevPos.getX() + points.get(i + 2).getX(), prevPos.getY() + points.get(i + 2).getY());
        }

        return BoundingBox.mergeBoundingBoxes(boxes);
    }

    public BoundingBox getBoundingBoxForL(Point2D prevPos) {
        List<Point2D> newPoints = getAbsolutePointsRelativeToStartPos(prevPos);
        BoundingBox box = new BoundingBox(Math.min(prevPos.getX(), newPoints.get(0).getX()), Math.max(prevPos.getX(), newPoints.get(0).getX()),
                Math.min(prevPos.getY(), newPoints.get(0).getY()), Math.max(prevPos.getY(), newPoints.get(0).getY()));

        prevPos.setLocation(prevPos.getX() + points.get(0).getX(), prevPos.getY() + points.get(0).getY());

        return box;
    }

    /**
     * convert points from svg to standard coordiante system, as svg starts at top left corner
     * @param startPos
     * @return
     */
    public List<Point2D> getAbsolutePointsRelativeToStartPos(Point2D startPos) {

        List<Point2D> newPoints= points.stream().map(e ->  { return new Point2D.Double(e.getX() + startPos.getX(), startPos.getY() - e.getY());
        }).collect(Collectors.toList());

        return newPoints;
    }

    public Point2D getLastPoint() {
        return points.get(points.size() - 1);
    }

    private Point2D getLhCoord(List<Double> points) {
        double a = 3 * points.get(3) - 9 * points.get(2) + 9 * points.get(1) - 3 * points.get(0);
        double b = 6 * points.get(0) - 12 * points.get(1) + 6 * points.get(2);
        double c = 3 * points.get(1) - 3 * points.get(0);

        double disc = b*b -4 * a *c;
        double xl = points.get(0);
        double xh = points.get(0);

        if (points.get(3) < xl) {
            xl = points.get(3);
        }

        if (points.get(3) > xh) {
            xh = points.get(3);
        }

        if (disc >= 0) {
            double t1 = (-b + Math.sqrt(disc)) / (2 * a);

            if (t1 > 0 && t1 < 1) {
                double x1 = points.get(0) * Math.pow((1 - t1), 3)
                        + 3 * points.get(1) * t1 * Math.pow((1-t1), 2)
                        + 3 * points.get(2) * t1 * t1 * (1 - t1)
                        + points.get(3) * Math.pow(t1, 3);

                if (x1 < xl) {
                    xl = x1;
                }

                if (x1 > xh) {
                    xh = x1;
                }
            }

            double t2 = (-b - Math.sqrt(disc)) / (2 * a);

            if (t2 > 0 && t2 < 1) {
                double x2 = points.get(0) * Math.pow((1 - t2), 3)
                        + 3 * points.get(1) * t2 * Math.pow((1-t2), 2)
                        + 3 * points.get(2) * t2 * t2 * (1 - t2)
                        + points.get(3) * Math.pow(t2, 3);

                if (x2 < xl) {
                    xl = x2;
                }

                if (x2 > xh) {
                    xh = x2;
                }
            }
        }

        return new Point2D.Double(xl, xh);
    }


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
