package com.michalso.svaggy.display.SvgElements.Basic;

import java.awt.geom.Point2D;

public class SvgTransform extends  SvgElement {

    private boolean isMatrix;
    private Point2D scale = new Point2D.Double(1, 1);
    private Point2D translate = new Point2D.Double(0, 0);
    private Rotate rotate = new Rotate();
    private Point2D skew = new Point2D.Double(0, 0);

    static class Rotate {
        double angle;
        Point2D rotPoint;
    }

    public SvgTransform() {

    }

    public SvgTransform(Point2D scale, Point2D translate, Rotate rotate) {
        this.scale = scale;
        this.translate = translate;
        this.rotate = rotate;
    }

    public SvgTransform(double scaleX, double scaleY, double transX, double transY) {
        this.scale = new Point2D.Double(scaleX, scaleY);
        this.translate = new Point2D.Double(transX, transY);
    }

    public SvgTransform(double scaleX, double scaleY, double transX, double transY, double skewX, double skewY) {
        this.scale = new Point2D.Double(scaleX, scaleY);
        this.translate = new Point2D.Double(transX, transY);
        this.skew = new Point2D.Double(skewX, skewY);
    }

    //Move to another position, local coordiante system is different if scale != 1
    public void move(Point2D moveOffset) {
        double newTransX = (1 - scale.getX()) * moveOffset.getX() + translate.getX();
        double newTransY = (1 - scale.getY()) * moveOffset.getY() + translate.getY();

        translate = new Point2D.Double(newTransX, newTransY);
    }

    public void clearTranslation() {
        translate = new Point2D.Double(0, 0);
    }

    @Override
    public String getSvgString() {
        return null;
    }

    @Override
    public String getSvgStringWithoutTag() {
        if (isMatrix) {
            return String.format("matrix(%f,%f,%f,%f,%.4f,%.4f)", scale.getX(), skew.getX(), skew.getY(), scale.getY(), translate.getX(), translate.getY());
        }
        else {
            //todo
            return null;
        }
    }

    @Override
    public SvgTransform cloneObject() {
        return new SvgTransform(scale, translate, rotate);
    }

    public Point2D getScale() {
        return scale;
    }

    public void setScale(Point2D scale) {
        this.scale = scale;
    }

    public Point2D getTranslate() {
        return translate;
    }

    public void setTranslate(Point2D translate) {
        this.translate = translate;
    }

    public Rotate getRotate() {
        return rotate;
    }

    public void setRotate(Rotate rotate) {
        this.rotate = rotate;
    }

    public boolean isMatrix() {
        return isMatrix;
    }

    public void setMatrix(boolean matrix) {
        isMatrix = matrix;
    }
}
