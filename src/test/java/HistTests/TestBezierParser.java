package HistTests;

import com.michalso.svaggy.display.SvgElements.Bezier.BezierFragment;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierParser;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;
import org.junit.BeforeClass;
import org.junit.Test;
import org.thymeleaf.standard.expression.Fragment;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBezierParser {


    static String pathString;
    BezierParser bezierParser = new BezierParser();


    @BeforeClass
    public static void init() throws IOException {
        pathString = new String(Files.readAllBytes(Paths.get("src/test/resources/groupPath.xml")));
    }

    @Test
    public void testPath() throws ParserConfigurationException, SAXException, IOException {
        BezierPath bezierPath = bezierParser.parse(pathString);
        assertEquals(bezierPath.getFragments().size(), 7);
        assertEquals(bezierPath.getFragments().get(0).getPoints().size(), 1);
        assertEquals(bezierPath.getFragments().get(0).getPoints().get(0),
                new Point2D.Double(334.13082,174.50695));
    }
}
