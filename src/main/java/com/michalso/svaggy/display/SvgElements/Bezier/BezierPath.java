package com.michalso.svaggy.display.SvgElements.Bezier;

import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;
import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BezierPath extends SvgElement {
    private List<BezierFragment> fragments = new ArrayList<>();
    public BezierPath() {

    }

    public BezierPath(BezierPath bp) {
        this.fragments = new ArrayList<>();
        bp.fragments.forEach(f -> fragments.add(new BezierFragment(f)));

        if (bp.styleElement.isPresent()) {
            this.styleElement = Optional.of(new StyleElement(bp.getStyleElement().get()));
        }
    }

    public BezierPath(String svgString) throws IOException, SAXException, ParserConfigurationException {
        fromSvgString(svgString);
    }

    //todo remove?
    public static BezierPath fromSvgString(String string) throws ParserConfigurationException, SAXException, IOException {
        BezierParser parser = new BezierParser();
        BezierPath path =  parser.parse(string);
        return path;
    }

    //includes <path prefix
    @Override
    public String getSvgString() {
        SvgParserReader parser = new SvgXmlParserReader();
        parser.addTag("path");
        super.addElementsToParse(parser);
        parser.addElement("d", getFragmentsString());

        return parser.getString();
    }

    @Override
    public BezierPath cloneObject() {
        BezierPath clonePath = new BezierPath(this);
        super.cloneSvgElements(clonePath);

        return clonePath;
    }

    public String getFragmentsString() {
        String path = "";
        boolean isFirst = true;
        for (BezierFragment fragment : fragments) {
            if (isFirst) {
                path += fragment.getString();
                isFirst = false;
            } else {
                path += " " + fragment.getString();
            }
        }

        return path;
    }

    public List<Integer> getIndexesForFragments(String type){
        List<Integer> indexes=  IntStream.range(0, getFragments().size()).filter(idx -> getFragments().get(idx).
                getType().equals(type)).boxed().collect(Collectors.toList());

        return indexes;
    }

    public void addFragment(BezierFragment fragment) {
        fragments.add(fragment);
    }

    public List<BezierFragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<BezierFragment> fragments) {
        this.fragments = fragments;
    }

}
