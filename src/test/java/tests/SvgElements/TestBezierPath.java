package tests.SvgElements;

import com.michalso.svaggy.display.SvgElements.Basic.BoundingBox;
import com.michalso.svaggy.display.SvgElements.Basic.SvgGroup;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierFragment;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;
import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserWriter;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserWriter;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBezierPath {

    @Test
    public void testBezierPathWithFragments() {
        BezierPath path = new BezierPath();
        path.addFragment(new BezierFragment("m", List.of(new Point2D.Double(334.13082,174.50695))));
        path.addFragment(new BezierFragment("c", List.of(new Point2D.Double(-77.30898,-20.7144),
                new Point2D.Double(-110.01186,37.60092), new Point2D.Double(-120.1431,75.411))));
        path.addFragment(new BezierFragment("l", List.of(new Point2D.Double(-111.88128,417.5472))));

        assertEquals("<path d=\"m 334.13082,174.50695 c -77.30898,-20.7144 -110.01186,37.60092 -120.1431,75.411 l -111.88128,417.5472\"/>", path.getSvgString());
    }

    @Test
    public void testBezierPathWithFragmentsAndStyle() {
        BezierPath path = new BezierPath();
        path.addFragment(new BezierFragment("m", List.of(new Point2D.Double(334.13082,174.50695))));
        path.addFragment(new BezierFragment("c", List.of(new Point2D.Double(-77.30898,-20.7144),
                new Point2D.Double(-110.01186,37.60092), new Point2D.Double(-120.1431,75.411))));
        path.addFragment(new BezierFragment("l", List.of(new Point2D.Double(-111.88128,417.5472))));

        StyleElement style = new StyleElement();
        style.setOpacity(1);
        style.setStroke("#000000");
        path.setStyleElement(style);

        assertEquals("<path style=\"opacity:1;stroke:#000000\" d=\"m 334.13082,174.50695 c -77.30898,-20.7144 -110.01186,37.60092 -120.1431,75.411 l -111.88128,417.5472\"/>", path.getSvgString());
    }

    @Test
    public void testBoundingBoxSinglePath() throws IOException, ParserConfigurationException, SAXException {
        SvgParserWriter svgParserWriter= new SvgXmlParserWriter();
        String string = new String(Files.readAllBytes(Paths.get("src/test/resources/wip.xml")));
        SvgRoot root = svgParserWriter.parse(string);

        List<SvgGroup> groups = root.getGroups();
        BoundingBox box = groups.get(0).getBoundingBox();

        System.out.println(box);
        System.out.println(box.toSvgRect());
    }
}
