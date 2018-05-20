package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Bezier.BezierFragment;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;
import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBezierPath {

    @Test
    public void testBezierPathWithFragments() {
        BezierPath path = new BezierPath();
        path.addFragment(new BezierFragment("m", List.of(new Point2D.Double(334.13082,174.50695))));
        path.addFragment(new BezierFragment("c", List.of(new Point2D.Double(-77.30898,-20.7144),
                new Point2D.Double(-110.01186,37.60092), new Point2D.Double(-120.1431,75.411))));
        path.addFragment(new BezierFragment("l", List.of(new Point2D.Double(-111.88128,417.5472))));

        assertEquals(path.getSvgString(), "<path d=\"m 334.13082,174.50695 c -77.30898,-20.7144 -110.01186,37.60092 -120.1431,75.411 l -111.88128,417.5472\"/>");
    }

    @Test
    public void testBezierPathWithFragmentsAndStyle() {
        BezierPath path = new BezierPath();
        path.addFragment(new BezierFragment("m", List.of(new Point2D.Double(334.13082,174.50695))));
        path.addFragment(new BezierFragment("c", List.of(new Point2D.Double(-77.30898,-20.7144),
                new Point2D.Double(-110.01186,37.60092), new Point2D.Double(-120.1431,75.411))));
        path.addFragment(new BezierFragment("l", List.of(new Point2D.Double(-111.88128,417.5472))));

        StyleElement style = new StyleElement();
        style.setOpacity(1);
        style.setStroke("#000000");
        path.setStyleElement(style);

        assertEquals(path.getSvgString(), "<path style=\"opacity:1;stroke:#000000\" d=\"m 334.13082,174.50695 c -77.30898,-20.7144 -110.01186,37.60092 -120.1431,75.411 l -111.88128,417.5472\"/>");
    }
}
