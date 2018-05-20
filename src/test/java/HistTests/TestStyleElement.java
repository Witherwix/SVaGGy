package HistTests;

import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TestStyleElement {

    String STYLE_STRING="opacity:1;fill:#ffe0bd;fill-opacity:1;stroke:#000000;stroke-width:12.60000038;" +
            "stroke-linecap:square;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;" +
            "stroke-dashoffset:109;stroke-opacity:1";

    @Test
    public void testFromSvgString() {
        StyleElement styleElement = new StyleElement();
        styleElement.fromSvgString(STYLE_STRING);

        assertEquals(styleElement.getOpacity(), Optional.of(1));
        assertEquals(styleElement.getFill(), Optional.of("#ffe0bd"));
        assertEquals(styleElement.getFillOpacity(), Optional.of(1));
        assertEquals(styleElement.getStroke(), Optional.of("#000000"));
        assertEquals(styleElement.getStrokeWidth(), Optional.of(12.60000038));
        assertEquals(styleElement.getStrokeLinecap(), Optional.of("square"));
        assertEquals(styleElement.getStrokeLineJoin(), Optional.of("miter"));
        assertEquals(styleElement.getStrokeMiterlimit(), Optional.of(4));
        assertEquals(styleElement.getStrokeDashArray(), Optional.of("none"));
        assertEquals(styleElement.getStrokeDashOffset(), Optional.of(109));
        assertEquals(styleElement.getStrokeOpacity(), Optional.of(1));
    }

    @Test
    public void testGetSvgString() {
        StyleElement styleElement = new StyleElement();
        styleElement.fromSvgString(STYLE_STRING);
        String retString = styleElement.getSvgString();

        assertEquals(retString, "style=\"" + STYLE_STRING + "\"");
    }
}
