package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Basic.SvgCircle;
import javafx.scene.shape.Circle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSvgCircle {

    @Test
    public void testBasicCircle() {
        SvgCircle circle = new SvgCircle(10, 20, 60);

        assertEquals("<circle cx=\"10.0\" cy=\"20.0\" r=\"60.0\"/>", circle.getSvgString());
    }
}
