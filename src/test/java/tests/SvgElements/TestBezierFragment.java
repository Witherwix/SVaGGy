package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Basic.BoundingBox;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierFragment;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testAbsoletePointsRelativetoStartPos() {
        List<Point2D> points= List.of(new Point2D.Double(0,0), new Point2D.Double(-185.71428,203.80953), new Point2D.Double(-120,334.2));
        BezierFragment bezFragment = new BezierFragment("c", points);
        List<Point2D> retPoints = bezFragment.getAbsolutePointsRelativeToStartPos(new Point2D.Double(100, 100));

        assertEquals(retPoints.get(0), new Point2D.Double(100, 100));
        assertEquals(retPoints.get(1), new Point2D.Double(-85.71428, -103.80953));
        assertEquals(retPoints.get(2), new Point2D.Double(-20, -234.2));
    }

    @Test
    public void testBoundingBoxPreviousElementM() {
        List<Point2D> points= List.of(new Point2D.Double(0,0), new Point2D.Double(-185.714286,203.80953), new Point2D.Double(-120,334.28572));

        BezierFragment bezFragment = new BezierFragment("c", points);
        Optional<BoundingBox> box = bezFragment.getBoundingBox(new Point2D.Double(205.71429,166.64792));

        BoundingBox expBox = new BoundingBox(71.638873247938, 205.71429, -167.63780000000003, 166.64792);
        assertTrue(expBox.equals(box.get()));
    }
}
