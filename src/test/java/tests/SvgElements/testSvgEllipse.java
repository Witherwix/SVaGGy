package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Basic.SvgEllipse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testSvgEllipse {

    @Test
    public void testSimpleEllipse() {
        SvgEllipse ellipse = new SvgEllipse(100, 50, 100, 50);
        assertEquals(ellipse.getSvgString(), "<ellipse cx=\"100.0\" cy=\"50.0\" rx=\"100.0\" ry=\"50.0\"/>");
    }
}
