package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserWriter;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserWriter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TestSvgWriter {

    static String pathString;
    static String pathStyleString;

    @BeforeClass
    public static void init() throws IOException {
        pathString = new String(Files.readAllBytes(Paths.get("src/test/resources/groupPath.xml")));
        pathStyleString = new String(Files.readAllBytes(Paths.get("src/test/resources/groupPathStyle.xml")));
    }

    @Test
    public void testParseSimpleSvg() throws IOException, SAXException, ParserConfigurationException {
        SvgParserWriter svgWriter = new SvgXmlParserWriter();
        SvgRoot svgRoot= svgWriter.parse(pathString);

        assertEquals(pathString, svgRoot.getSvgString());
    }

    @Test
    public void testParseSvgGroupPathStyle() throws IOException, SAXException, ParserConfigurationException {
        SvgParserWriter svgWriter = new SvgXmlParserWriter();
        SvgRoot svgRoot= svgWriter.parse(pathStyleString);

        //System.out.println(svgRoot.getSvgString());
        assertEquals(pathStyleString, svgRoot.getSvgString());
    }

    @Test
    public void testParseSvgDefsLinearGradients() throws IOException, ParserConfigurationException, SAXException {
        SvgParserWriter svgWriter = new SvgXmlParserWriter();
        String svgString = new String(Files.readAllBytes(Paths.get("src/test/resources/svgDefsLinGrad.xml")));
        SvgRoot svgRoot= svgWriter.parse(svgString);

        assertEquals(svgString, svgRoot.getSvgString());
    }

    @Test
    public void testParseGroupEllipseStyle() throws IOException, SAXException, ParserConfigurationException {
        SvgParserWriter svgWriter = new SvgXmlParserWriter();
        String svgString = new String(Files.readAllBytes(Paths.get("src/test/resources/groupEllipseStyle.xml")));
        SvgRoot svgRoot= svgWriter.parse(svgString);

        assertEquals(svgString, svgRoot.getSvgString());
    }

    @Test
    public void testParseGroupGroupPath() throws IOException, ParserConfigurationException, SAXException {
        SvgParserWriter svgWriter = new SvgXmlParserWriter();
        String svgString = new String(Files.readAllBytes(Paths.get("src/test/resources/groupGroupPath.xml")));

        SvgRoot svgRoot = svgWriter.parse(svgString);

        assertEquals(svgString, svgRoot.getSvgString());
    }

    @Test
    public void testParseGroupCircle() throws IOException, ParserConfigurationException, SAXException {
        SvgParserWriter svgWriter = new SvgXmlParserWriter();
        String svgString = new String(Files.readAllBytes(Paths.get("src/test/resources/groupCircle.xml")));
        SvgRoot svgRoot = svgWriter.parse(svgString);

        assertEquals(svgString, svgRoot.getSvgString());
    }
}
