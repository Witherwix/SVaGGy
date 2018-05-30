package com.michalso.svaggy.display.SvgElements.Basic;

import java.awt.geom.Point2D;

public interface Boundable {
    BoundingBox getBoundingBox();
    void move(Point2D pos);
}
