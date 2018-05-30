package com.michalso.svaggy.display.SvgElements.Basic;

import com.michalso.svaggy.display.Utils.SvgUtils;

import java.awt.geom.Point2D;
import java.util.List;


/*
Bounding box for svg elemenet, it uses coordinates as svg
 */
public class BoundingBox {

    private Point2D leftDownCorner;
    private Point2D leftUpCorner;
    private Point2D rightUpCorner;
    private Point2D rightDownCorner;

    public BoundingBox() {

    }

    public BoundingBox(Point2D leftDownCorner, Point2D leftUpCorner, Point2D rightUpCorner, Point2D rightDownCorner) {
        this.leftDownCorner = leftDownCorner;
        this.leftUpCorner = leftUpCorner;
        this.rightUpCorner = rightUpCorner;
        this.rightDownCorner = rightDownCorner;
    }

    public BoundingBox(double minX, double maxX, double minY, double maxY) {
        this.leftDownCorner = new Point2D.Double(minX, maxY);
        this.leftUpCorner = new Point2D.Double(minX, minY);
        this.rightUpCorner = new Point2D.Double(maxX, minY);
        this.rightDownCorner = new Point2D.Double(maxX, maxY);
    }

    public static BoundingBox mergeBoundingBoxes(BoundingBox... boxes) {
        BoundingBox retBox = boxes[0];
        for (int i=1; i<boxes.length; i++) {
            double minX =  Math.min(boxes[i].leftDownCorner.getX(), retBox.leftDownCorner.getX());
            double maxX =  Math.max(boxes[i].rightDownCorner.getX(), retBox.rightDownCorner.getX());
            double minY =  Math.min(boxes[i].rightUpCorner.getY(), retBox.rightUpCorner.getY());
            double maxY =  Math.max(boxes[i].rightDownCorner.getY(), retBox.rightDownCorner.getY());

            retBox = new BoundingBox(minX, maxX, minY, maxY);
        }

        return retBox;
    }

    public BoundingBox getScaledBox(Point2D scale) {
        double newWidth = getWidth() * scale.getX() / 2;
        double newHeight = getHeight() * scale.getY() / 2;

        Point2D newLeftUp = new Point2D.Double(leftUpCorner.getX() - newWidth, leftUpCorner.getY() - newHeight);
        Point2D newLeftDown = new Point2D.Double(leftDownCorner.getX() - newWidth, leftDownCorner.getY() + newHeight);
        Point2D newRightUp = new Point2D.Double(rightUpCorner.getX() + newWidth, rightUpCorner.getY() - newHeight);
        Point2D newRightDown = new Point2D.Double(rightDownCorner.getX() + newWidth, rightDownCorner.getY() + newHeight);

        return new BoundingBox(newLeftDown, newLeftUp, newRightUp, newRightDown);
    }

    public static BoundingBox mergeBoundingBoxes(List<BoundingBox> boxes) {
        if (boxes.size() == 0) {
            return null;
        }
        BoundingBox retBox = boxes.get(0);
        for (int i=1; i<boxes.size(); i++) {

            if (boxes.get(i) == null)
            {
                continue;
            }

            double minX =  Math.min(boxes.get(i).leftDownCorner.getX(), retBox.leftDownCorner.getX());
            double maxX =  Math.max(boxes.get(i).rightDownCorner.getX(), retBox.rightDownCorner.getX());
            double minY =  Math.min(boxes.get(i).rightUpCorner.getY(), retBox.rightUpCorner.getY());
            double maxY =  Math.max(boxes.get(i).rightDownCorner.getY(), retBox.rightDownCorner.getY());

            retBox = new BoundingBox(minX, maxX, minY, maxY);
        }
        return retBox;
    }

    public String toSvgRect() {
        String retString = "<rect width=" + SvgUtils.quoteValue(getWidth()) + " " + "height=" + SvgUtils.quoteValue(getHeight())
                + " x=" + SvgUtils.quoteValue(leftUpCorner.getX()) + " y=" + SvgUtils.quoteValue(leftUpCorner.getY()) + " style=\"stroke:pink;fill:transparent;\" />\n";

        return retString;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder(",");
        builder.append(leftDownCorner);
        builder.append(leftUpCorner);
        builder.append(rightUpCorner);
        builder.append(rightDownCorner);
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!BoundingBox.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final BoundingBox box = (BoundingBox)obj;

        if(!leftDownCorner.equals(box.leftDownCorner)) {
            return false;
        }

        if(!leftUpCorner.equals(leftUpCorner)) {
            return false;
        }

        if(!rightDownCorner.equals(box.rightDownCorner)) {
            return false;
        }

        if(!rightUpCorner.equals(box.rightUpCorner)) {
            return false;
        }

        return true;
    }

    public double getHeight() {
        return Math.abs(leftDownCorner.getY() - leftUpCorner.getY());
    }

    public double getWidth() {
        return Math.abs(leftDownCorner.getX() - rightDownCorner.getX());
    }

    public Point2D getLeftDownCorner() {
        return leftDownCorner;
    }

    public void setLeftDownCorner(Point2D leftDownCorner) {
        this.leftDownCorner = leftDownCorner;
    }

    public Point2D getLeftUpCorner() {
        return leftUpCorner;
    }

    public void setLeftUpCorner(Point2D leftUpCorner) {
        this.leftUpCorner = leftUpCorner;
    }

    public Point2D getRightUpCorner() {
        return rightUpCorner;
    }

    public void setRightUpCorner(Point2D rightUpCorner) {
        this.rightUpCorner = rightUpCorner;
    }

    public Point2D getRightDownCorner() {
        return rightDownCorner;
    }

    public void setRightDownCorner(Point2D rightDownCorner) {
        this.rightDownCorner = rightDownCorner;
    }
}
