package tests.SvgElements;


import com.michalso.svaggy.display.SvgElements.Basic.SvgDefinition;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TestSvgRoot {

    @Test
    public void testGetSvgStringWidthHeight() {
        SvgRoot svgRoot = new SvgRoot(100, 200);
        assertEquals(svgRoot.getSvgString(), "<svg width=\"100\" height=\"200\"/>" );
    }

    @Test
    public void testGetSvgStringWithDEfinition() {
        SvgRoot svgRoot = new SvgRoot(100, 200);
        SvgDefinition def = new SvgDefinition();
        svgRoot.setSvgDefinition(new SvgDefinition());
        assertEquals(svgRoot.getSvgString(), "<svg width=\"100\" height=\"200\">\n<defs/>\n</svg>" );
    }


}
