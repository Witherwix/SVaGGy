package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestSvgStyle {

    @Test
    public void testStyleWithAllParams() {
        StyleElement styleElement = new StyleElement();

        styleElement.setOpacity(1);
        styleElement.setFill("url(#linearGradient5624)");
        styleElement.setFillOpacity(1);
        styleElement.setStroke("#000000");
        styleElement.setStrokeWidth(12.60000038);
        styleElement.setStrokeLinecap("square");
        styleElement.setStrokeLineJoin("miter");
        styleElement.setStrokeMiterlimit(4);
        styleElement.setStrokeDashArray("none");
        styleElement.setStrokeDashOffset(109);
        styleElement.setStrokeOpacity(1);

        assertEquals(styleElement.getSvgStringWithoutTag(), "\"opacity:1;fill:url(#linearGradient5624);" +
                "fill-opacity:1;stroke:#000000;stroke-width:12.60000038;stroke-linecap:square;stroke-linejoin:miter;" +
                "stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:109;stroke-opacity:1\"");

    }

    @Test
    public void testStyleWithStrokeOpacity() {
        StyleElement styleElement = new StyleElement();
        styleElement.setStrokeOpacity(10);

        assertEquals(styleElement.getSvgStringWithoutTag(), "\"stroke-opacity:10\"");
    }
}
