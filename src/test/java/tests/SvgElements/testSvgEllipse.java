package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Basic.SvgEllipse;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserWriter;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserWriter;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class testSvgEllipse {

    @Test
    public void testSimpleEllipse() {
        SvgEllipse ellipse = new SvgEllipse(100, 50, 100, 50);
        assertEquals("<ellipse cx=\"100.0\" cy=\"50.0\" rx=\"100.0\" ry=\"50.0\"/>", ellipse.getSvgString());
    }

    @Test
    public void testEllipseWithTransform() throws IOException, ParserConfigurationException, SAXException {
        SvgParserWriter svgParserWriter= new SvgXmlParserWriter();
        String string = new String(Files.readAllBytes(Paths.get("src/test/resources/ellipseTransform.svg")));
        SvgRoot root = svgParserWriter.parse(string);

        assertEquals(string, root.getSvgString());

    }
}
