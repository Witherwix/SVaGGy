package HistTests;

import com.michalso.svaggy.display.SvgElements.Bezier.BezierFragment;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBezierPath {

    static String pathString;

    @BeforeClass
    public static void init() throws IOException {
        pathString = new String(Files.readAllBytes(Paths.get("src/test/resources/groupPath.xml")));
    }

    @Test
    public void testParseSvgString() throws IOException, SAXException, ParserConfigurationException {
        BezierPath bezierPath = BezierPath.fromSvgString(pathString);

        assertTrue(bezierPath.getFragments().size() > 0);
        assertTrue(bezierPath.getFragments().stream().filter(f ->f.getType().equals("m"))
                .collect(Collectors.toList()).size() == 1);

        assertTrue(bezierPath.getFragments().stream().filter(f ->f.getType().equals("l"))
                .collect(Collectors.toList()).size() == 2);

        assertTrue(bezierPath.getFragments().stream().filter(f ->f.getType().equals("z"))
                .collect(Collectors.toList()).size() == 1);

        assertTrue(bezierPath.getFragments().stream().filter(f ->f.getType().equals("c"))
                .collect(Collectors.toList()).size() == 3);

        assertTrue(bezierPath.getStyleElement().isPresent());
    }

    @Test
    public void testGetFragmentString() throws IOException, SAXException, ParserConfigurationException {
        BezierPath bezierPath = BezierPath.fromSvgString(pathString);
        String retString = bezierPath.getFragmentsString();
        assertEquals(retString,"m 334.13082,174.50695 c -77.30898,-20.7144 -110.01186,37.60092 -120.1431,75.411 l -111.88128,417.5472 c -10.130816,37.81008 -9.400856,98.81928 67.90728,119.53452 77.30856,20.7144 108.44568,-31.7562 118.57692,-69.56628 l 111.88128,-417.5472 c 10.13124,-37.81008 10.96704,-104.664 -66.3411,-125.37924 z");
    }

    @Test
    public void testSvgString() throws IOException, SAXException, ParserConfigurationException {
        String shortPath = new String(Files.readAllBytes(Paths.get("src/test/resources/pathWithDAndStyle.xml")));
        BezierPath bezierPath = BezierPath.fromSvgString(shortPath);
        String retString = bezierPath.getSvgString();

        assertEquals(retString, shortPath);
    }

    @Test
    public void testGetIndexesForFragments() throws IOException, SAXException, ParserConfigurationException {
        BezierPath bezierPath = BezierPath.fromSvgString(pathString);
        List<Integer> indexes = bezierPath.getIndexesForFragments("c");

        assertEquals(indexes, List.of(1, 3, 5));
    }
}
