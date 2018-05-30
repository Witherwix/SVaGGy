package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Basic.SvgTransform;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class TestSvgTransform {


    @Test
    public void testMoveWithScale3() {
        SvgTransform transform= new SvgTransform(3, 3, 0, 0);
        transform.move(new Point2D.Double(100, -100));

        assertEquals(new Point2D.Double(-200, 200), transform.getTranslate());

    }
}
