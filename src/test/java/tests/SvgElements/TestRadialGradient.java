package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Gradients.RadialGradient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRadialGradient {

    @Test
    public void testRadialGradientWithAllParams() {
        RadialGradient radGrad= new RadialGradient();
        radGrad.setCx(20);
        radGrad.setCy(20);
        radGrad.setR(50);
        radGrad.setFx(20);
        radGrad.setFy(20);

        assertEquals("<RadialGradient cx=\"20%\" cy=\"20%\" r=\"50%\" fx=\"20%\" fy=\"20%\"/>", radGrad.getSvgString());
    }
}
