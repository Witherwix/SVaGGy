package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Gradients.SvgStopElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSvgStopElement {

    @Test
    public void testGetStringEmptyStyle() {
        SvgStopElement stopElm = new SvgStopElement();
        stopElm.setOffset(10);
        stopElm.setStopColor("#AAAAAA");
        stopElm.setStopOpacity(10);

        assertEquals(stopElm.getSvgString(), "<stop offset=\"10\" stop-color=\"#AAAAAA\" stop-opacity=\"10\"/>");
    }
}
