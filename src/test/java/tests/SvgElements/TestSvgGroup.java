package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Bezier.BezierFragment;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;
import com.michalso.svaggy.display.SvgElements.Basic.SvgGroup;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSvgGroup {

    @Test
    public void testEmptyGroup() {
        SvgGroup group = new SvgGroup();

        assertEquals(group.getSvgString(), "<g/>");
    }

    @Test
    public void testGroupWith2Paths() {
        SvgGroup group = new SvgGroup();

        BezierPath path = new BezierPath();
        path.addFragment(new BezierFragment("m", List.of(new Point2D.Double(334.13082,174.50695))));
        path.addFragment(new BezierFragment("c", List.of(new Point2D.Double(-77.30898,-20.7144),
                new Point2D.Double(-110.01186,37.60092), new Point2D.Double(-120.1431,75.411))));
        path.addFragment(new BezierFragment("l", List.of(new Point2D.Double(-111.88128,417.5472))));

        group.addElement(path);
        group.addElement(path);

        assertEquals(group.getSvgString(), "<g>\n<path d=\"m 334.13082,174.50695 c -77.30898,-20.7144" +
                " -110.01186,37.60092 -120.1431,75.411 l -111.88128,417.5472\"/>\n<path d=\"m 334.13082,174.50695" +
                " c -77.30898,-20.7144 -110.01186,37.60092 -120.1431,75.411 l -111.88128,417.5472\"/>\n</g>");
    }
}
