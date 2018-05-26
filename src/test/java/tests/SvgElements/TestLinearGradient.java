package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Gradients.LinearGradient;
import com.michalso.svaggy.display.SvgElements.Gradients.SvgStopElement;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TestLinearGradient {


    @Test
    public void testLinearGradientWith2Params() {
        LinearGradient linGrad = new LinearGradient();
        linGrad.setX1(10.0);
        linGrad.setY2(30.0);

        assertEquals("<linearGradient x1=\"10\" y2=\"30\"/>", linGrad.getSvgString());
    }

    @Test
    public void testLinearGradientWith2tops() {
        LinearGradient linGrad = new LinearGradient();
        SvgStopElement stop1 = new SvgStopElement();
        SvgStopElement stop2 = new SvgStopElement();

        stop1.setOffset(1);
        stop1.setStopColor("#AAAAAA");
        stop1.setStopOpacity(100);

        stop2.setOffset(10);
        stop2.setStopColor("#AAAABB");
        stop2.setStopOpacity(80);

        linGrad.addStopElement(stop1);
        linGrad.addStopElement(stop2);

        assertEquals("<linearGradient>\n<stop offset=\"1\" stop-color=\"#AAAAAA\"" +
                " stop-opacity=\"100\"/>\n<stop offset=\"10\" stop-color=\"#AAAABB\" stop-opacity=\"80\"/>\n</linearGradient>", linGrad.getSvgString());
    }
}
