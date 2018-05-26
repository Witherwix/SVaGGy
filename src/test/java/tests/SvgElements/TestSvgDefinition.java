package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Gradients.LinearGradient;
import com.michalso.svaggy.display.SvgElements.Basic.SvgDefinition;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TestSvgDefinition {

    @Test
    public void testEmptySvgDefinition() {
        SvgDefinition svgDef = new SvgDefinition();

        assertEquals("<defs/>", svgDef.getSvgString());
    }

    @Test
    public void testSvgDefinitionWithGradient() {
        SvgDefinition svgDef = new SvgDefinition();
        LinearGradient linearGradient = new LinearGradient(10.0, 20.0, 100.0, 200.0);
        svgDef.addElement(linearGradient);
        assertEquals("<defs>\n<linearGradient x1=\"10\" y1=\"20\" x2=\"100\" y2=\"200\"/>\n</defs>", svgDef.getSvgString());
    }
}
