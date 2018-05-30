package com.michalso.svaggy.display.SvgElements.Bezier;

import com.michalso.svaggy.display.SvgElements.Basic.Boundable;
import com.michalso.svaggy.display.SvgElements.Basic.BoundingBox;
import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;
import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BezierPath extends SvgElement implements Boundable {
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

    @Override
    public BoundingBox getBoundingBox() {
        if (fragments.size() == 0) {
            return null;
        }

        List<BoundingBox> boxes = new ArrayList<>();


        Point2D lastPos = (Point2D)fragments.get(0).getPoints().get(0).clone();
        for (int i=1; i<fragments.size(); i++)
        {
            Optional<BoundingBox> box = fragments.get(i).getBoundingBox(lastPos);
            System.out.println(lastPos);
            if (box.isPresent()) {
                boxes.add(box.get());
            }
        }
        return BoundingBox.mergeBoundingBoxes(boxes);
    }

    @Override
    public void move(Point2D posOffset) {
        if (fragments.size() > 0) {
            if (fragments.get(0).getType().toUpperCase().equals("M")) {
                fragments.get(0).movePoint(0, posOffset);
            }
        }
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

    @Override
    public String toString() {

        String retString = fragments.stream().map(Object::toString).collect(Collectors.joining(", "));
        return retString;
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
