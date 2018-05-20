package HistTests;

import com.michalso.svaggy.display.SvgElements.Bezier.BezierFragment;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBezierFragment {

    @Test
    public void testChangeControlPoints() {
        List<Point2D> points= List.of(new Point2D.Double(1,1), new Point2D.Double(2, 2), new Point2D.Double(3, 3),
                new Point2D.Double(1,1), new Point2D.Double(2, 2), new Point2D.Double(3, 3));

        BezierFragment bezFragment = new BezierFragment("c", points);
        bezFragment.changeControlPoints(1, new Point2D.Double(1, 1), new Point2D.Double(100, 100));

        assertEquals(bezFragment.getPoints().get(3), new Point2D.Double(2, 2));
        assertEquals(bezFragment.getPoints().get(4), new Point2D.Double(102, 102));
        assertEquals(bezFragment.getPoints().get(5), new Point2D.Double(3, 3));
    }
}
