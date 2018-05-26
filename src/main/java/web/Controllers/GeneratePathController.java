package web.Controllers;

import com.michalso.svaggy.display.Generators.Generator.SvgGenerator;
import com.michalso.svaggy.display.Generators.Manipulators.BasicBezierFragmentManipulator;
import com.michalso.svaggy.display.Generators.Manipulators.BasicStyleManipulator;
import com.michalso.svaggy.display.Generators.Manipulators.StyleManipulator;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserWriter;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import web.Model.GeneratedPathData;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GeneratePathController {
    @GetMapping("/genBezierPaths")
    public String genBezierForm(Model model) {
        model.addAttribute("generatedPathData", new GeneratedPathData());
        return "formGenPaths";
    }

    @PostMapping("/genBezierPaths")
    public String generatePaths(@RequestParam("file") MultipartFile file, @ModelAttribute GeneratedPathData generatedPathData, Model model) throws IOException, ParserConfigurationException, SAXException {
        String svgString = new String(file.getBytes());

        SvgParserWriter svgParser = new SvgXmlParserWriter();
        SvgRoot svgRoot = svgParser.parse(svgString);
        SvgGenerator svgGenerator = new SvgGenerator();
        List<SvgRoot> svgs =  svgGenerator.generateCopies(50, svgRoot);

        StyleManipulator styleManipulator = new BasicStyleManipulator();
        svgs.forEach(e -> styleManipulator.changeFill(-40, 40, e));

        BasicBezierFragmentManipulator bezierManipulator = new BasicBezierFragmentManipulator();
        svgs.forEach(e -> bezierManipulator.changeControlPoints(generatedPathData.getFromOffset(), generatedPathData.getToOffset(), e));

        model.addAttribute("svgStrings", svgs.stream().map(SvgRoot::getSvgString).collect(Collectors.toList()));
        return "svgTest";
    }
}
