package web.Controllers;

import com.michalso.svaggy.display.Generators.Manipulators.BasicBezierFragmentManipulator;
import com.michalso.svaggy.display.Generators.Manipulators.BasicStyleManipulator;
import com.michalso.svaggy.display.Generators.Manipulators.GroupSplitter;
import com.michalso.svaggy.display.Generators.Manipulators.StyleManipulator;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;
import com.michalso.svaggy.display.SvgElements.Basic.SvgCircle;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserWriter;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserWriter;
import com.michalso.svaggy.display.Generators.Generator.SvgGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HelloController {

    @Autowired
    SvgGenerator svgGenerator;

    @GetMapping("/init")
    public String init() {
        return "init";
    }

    @GetMapping("/genCircles")
    public String generateCircles(Model model) {
        SvgCircle orgCircle = new SvgCircle(50, 50, 40);
        List<SvgCircle> circles = svgGenerator.generateCircles(orgCircle, 20);

        model.addAttribute("circles", circles);

        return "generatedCircles";
    }

    @GetMapping("/getPaths")
    public String generatePaths(Model model) throws ParserConfigurationException, SAXException, IOException {
        String svgString = new String(Files.readAllBytes(Paths.get("src/main/resources/utils/path.xml")));
        BezierPath bezierPath = BezierPath.fromSvgString(svgString);

        //List<BezierPath> paths = svgGenerator.generatePaths(bezierPath, 100);
        //List<List<BezierPath>> rowColPaths = Lists.partition(paths, 5);
        //model.addAttribute("paths", rowColPaths);
        return "generatedPaths";
    }

    @GetMapping("/getSvgTest")
    public String showSvg(Model model) throws IOException, ParserConfigurationException, SAXException {
        String svgString = new String(Files.readAllBytes(Paths.get("src/main/resources/utils/testManySvg.svg")));

        SvgParserWriter svgParser = new SvgXmlParserWriter();
        SvgRoot svgRoot = svgParser.parse(svgString);
        List<SvgRoot> svgs =  svgGenerator.generateCopies(50, svgRoot);

        StyleManipulator styleManipulator = new BasicStyleManipulator();
        svgs.forEach(e -> styleManipulator.changeFill(-40, 40, e));

        BasicBezierFragmentManipulator bezierManipulator = new BasicBezierFragmentManipulator();
        svgs.forEach(e -> bezierManipulator.changeControlPoints(-1, 1, e));

        model.addAttribute("svgStrings", svgs.stream().map(SvgRoot::getSvgString).collect(Collectors.toList()));
        return "svgTest";
    }

    @GetMapping("/getSplitGroups")
    public String showSplitGroups(Model model) throws IOException, ParserConfigurationException, SAXException {
        String svgString = new String(Files.readAllBytes(Paths.get("src/main/resources/utils/testManySvg.svg")));

        SvgParserWriter svgParser = new SvgXmlParserWriter();
        SvgRoot svgRoot = svgParser.parse(svgString);

        GroupSplitter groupSplitter = new GroupSplitter();
        List<SvgRoot> svgs = groupSplitter.splitGroupsToSvg(svgRoot);

        model.addAttribute("svgStrings", svgs.stream().map(SvgRoot::getSvgString).collect(Collectors.toList()));
        return "svgTest";
    }
}
