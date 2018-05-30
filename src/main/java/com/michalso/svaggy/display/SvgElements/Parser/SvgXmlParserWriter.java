package com.michalso.svaggy.display.SvgElements.Parser;

import com.michalso.svaggy.display.SvgElements.Basic.*;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierFragment;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;
import com.michalso.svaggy.display.SvgElements.Gradients.LinearGradient;
import com.michalso.svaggy.display.SvgElements.Gradients.SvgStopElement;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SvgXmlParserWriter implements SvgParserWriter {

    @Override
    public SvgRoot parse(String svgString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder dbBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(svgString));
        Document doc = dbBuilder.parse(is);

        SvgRoot root = new SvgRoot(100, 100); //todo cehck if not exists below
        for (int i=0; i <doc.getChildNodes().getLength(); i++) {
            Node node = doc.getChildNodes().item(i);
            parseRootNode(node, root);
        }

        return root;
    }

    private void parseRootNode(Node node, SvgRoot root) {
        NodeList children = node.getChildNodes();
        for (int i=0; i< children.getLength(); i++)
        {
            String childName = children.item(i).getNodeName();
            Node childNode = children.item(i);
            if (childName.equals("g"))
            {
                root.addGroup(parseGroup(childNode));
            }
            else if (childName.equals("defs")) {
                root.setSvgDefinition(parseDefinition(childNode));
            }
        }

        NamedNodeMap mapNodeAttrs= node.getAttributes();
        for (int j=0; j< mapNodeAttrs.getLength(); j++) {
            Node nodeAttr = mapNodeAttrs.item(j);

            switch (nodeAttr.getNodeName()) {
                case "width":
                    root.setWidth(Integer.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "height":
                    root.setHeight(Integer.valueOf(nodeAttr.getNodeValue()));
                    break;
            }

        }
    }

    private SvgGroup parseGroup(Node node){
        SvgGroup group = new SvgGroup();
        return parseTag(node, group, (svg, nextNode) -> {
            switch (nextNode.getNodeName()) {
                case "g":
                    svg.addElement(parseGroup(nextNode));
                    break;
                case "path":
                    svg.addElement(parsePath(nextNode));
                    break;
                case "ellipse":
                    svg.addElement(parseEllipse(nextNode));
                    break;
                case "circle":
                    svg.addElement(parseCircle(nextNode));
                    break;
            }

            return svg;
        });
    }

    private BezierPath parsePath(Node node) {
        BezierPath path = new BezierPath();
        path =  parseTag(node, path, (svg, nextNode) -> {
            switch (nextNode.getNodeName()) {

            }

            return svg;
        });

        NamedNodeMap mapNodeAttrs = node.getAttributes();
        for (int j=0; j< mapNodeAttrs.getLength(); j++) {
            Node nodeAttr = mapNodeAttrs.item(j);

            switch (nodeAttr.getNodeName()) {
                case "d":
                    path.setFragments(parseFragments(nodeAttr.getNodeValue()));
                    break;
                case "style":
                    path.setStyleElement(parseStyle(nodeAttr.getNodeValue()));
                    break;
            }

        }
        return path;
    }

    private SvgDefinition parseDefinition(Node node) {
        SvgDefinition def = new SvgDefinition();
        def =  parseTag(node, def, (svg, nextNode) -> {
            switch (nextNode.getNodeName()) {
                case "linearGradient":
                    svg.addElement(parseLinearGradient(nextNode));
                    break;
            }

            return svg;
        });


        return def;
    }

    private LinearGradient parseLinearGradient(Node node) {
        LinearGradient linGrad = new LinearGradient();
        linGrad =  parseTag(node, linGrad, (svg, nextNode) -> {
            switch (nextNode.getNodeName()) {
                case "stop":
                    svg.addStopElement(parseStop(nextNode));
                    break;
            }

            return svg;
        });

        NamedNodeMap mapNodeAttrs = node.getAttributes();
        for (int j=0; j< mapNodeAttrs.getLength(); j++) {
            Node nodeAttr = mapNodeAttrs.item(j);

            switch (nodeAttr.getNodeName()) {
                case "x1":
                    linGrad.setX1(Double.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "y1":
                    linGrad.setY1(Double.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "x2":
                    linGrad.setX2(Double.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "y2":
                    linGrad.setY2(Double.valueOf(nodeAttr.getNodeValue()));
                    break;
            }

        }

        return linGrad;
    }

    private SvgStopElement parseStop(Node node) {
        SvgStopElement stop = new SvgStopElement();
        stop =  parseTag(node, stop, (svg, nextNode) -> {
            switch (nextNode.getNodeName()) {

            }

            return svg;
        });

        NamedNodeMap mapNodeAttrs = node.getAttributes();
        for (int j=0; j< mapNodeAttrs.getLength(); j++) {
            Node nodeAttr = mapNodeAttrs.item(j);

            switch (nodeAttr.getNodeName()) {
                case "offset":
                    stop.setOffset(Integer.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "stop-color":
                    stop.setStopColor(nodeAttr.getNodeValue());
                    break;
                case "stop-opacity":
                    stop.setStopOpacity(Integer.valueOf(nodeAttr.getNodeValue()));
                    break;
            }

        }

        return stop;
    }

    private SvgEllipse parseEllipse(Node node) {
        SvgEllipse ellipse = new SvgEllipse();
        ellipse =  parseTag(node, ellipse, (svg, nextNode) -> {
            switch (nextNode.getNodeName()) {

            }

            return svg;
        });

        NamedNodeMap mapNodeAttrs = node.getAttributes();
        for (int j=0; j< mapNodeAttrs.getLength(); j++) {
            Node nodeAttr = mapNodeAttrs.item(j);

            switch (nodeAttr.getNodeName()) {
                case "cx":
                    ellipse.setCx(Double.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "cy":
                    ellipse.setCy(Double.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "rx":
                    ellipse.setRx(Double.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "ry":
                    ellipse.setRy(Double.valueOf(nodeAttr.getNodeValue()));
                    break;

            }
        }

        return ellipse;
    }

    private SvgCircle parseCircle(Node node) {
        SvgCircle circle = new SvgCircle();
        circle =  parseTag(node, circle, (svg, nextNode) -> {
            switch (nextNode.getNodeName()) {

            }

            return svg;
        });

        NamedNodeMap mapNodeAttrs = node.getAttributes();
        for (int j=0; j< mapNodeAttrs.getLength(); j++) {
            Node nodeAttr = mapNodeAttrs.item(j);

            switch (nodeAttr.getNodeName()) {
                case "cx":
                    circle.setCx(Double.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "cy":
                    circle.setCy(Double.valueOf(nodeAttr.getNodeValue()));
                    break;
                case "r":
                    circle.setR(Double.valueOf(nodeAttr.getNodeValue()));
                    break;

            }
        }

        return circle;
    }


    /*
    private <T> T parseAttributes(Node node, T elm, BiFunction<T, String, T> func) {
        NamedNodeMap mapNodeAttrs = node.getAttributes();
        for (int j=0; j< mapNodeAttrs.getLength(); j++) {
            Node nodeAttr = mapNodeAttrs.item(j);

            func.apply(elm, nodeAttr.getNodeName());

        }
    }*/


    private <T> T parseTag(Node node, T elm, BiFunction<T, Node, T> func) {
        NodeList children = node.getChildNodes();

        for (int i=0; i< children.getLength(); i++)
        {
            Node childNode = children.item(i);
            func.apply(elm, childNode);
        }

        if(elm instanceof SvgElement) {
            parseBase(node, (SvgElement)elm);
        }

        return elm;
    }
    //todo refactorise as performance might be improved
    private void parseBase(Node node, SvgElement svg) {
        NodeList children = node.getChildNodes();

        for (int i=0; i< children.getLength(); i++)
        {
            Node childNode = children.item(i);
            String childName = childNode.getNodeName();

            switch (childName) {
                case "style":
                    svg.setStyleElement(parseStyle(childNode.getNodeValue()));
                    break;
            }
        }

        NamedNodeMap mapNodeAttrs = node.getAttributes();
        for (int j=0; j< mapNodeAttrs.getLength(); j++) {
            Node nodeAttr = mapNodeAttrs.item(j);

            switch (nodeAttr.getNodeName()) {
                case "style":
                    svg.setStyleElement(parseStyle(nodeAttr.getNodeValue()));
                    break;
                case "transform":
                    svg.setTransform(parseTransform(nodeAttr.getNodeValue()));
                    break;
                case "id":
                    svg.setId(nodeAttr.getNodeValue());
                    break;
            }
        }
    }


    private List<BezierFragment> parseFragments(String string) {
        String[] parts =string.split(" ");

        List<BezierFragment> fragments = new ArrayList<>();
        for(int i=0; i<parts.length; i++) {
            switch(parts[i]) {
                case "C":
                case "c":
                case "l":
                case "L":
                case "M":
                case "m":
                case "Z":
                case "z":
                    fragments.add(new BezierFragment(parts[i]));
                    break;
                default:
                    parsePoints(fragments.get(fragments.size() - 1), i, parts);
            }
        }

        return fragments;
    }

    private int parsePoints(BezierFragment fragment, int index, String[] parts) {
        if (parts[index].contains(",")) { //multiple points
            String subparts[] = parts[index].split(",");

            for(int i=0; i<subparts.length; i+=2) {
                Point2D point2d = new Point2D.Double(Double.valueOf(subparts[i]), Double.valueOf(subparts[i+1]));
                fragment.addPoint(point2d);
            }

            index += subparts.length - 1;
        } else { //single point
            fragment.addPoint(new Point2D.Double(Double.valueOf(parts[index ]), Double.valueOf(parts[index + 1])));
            index += 1;
        }

        return index;
    }

    private SvgTransform parseTransform(String inputString) {
        SvgTransform svgTransform = new SvgTransform();
        if (inputString.contains("matrix")) {

            int beginIndex = inputString.indexOf("(") + 1;
            int endIndex = inputString.indexOf(")");

            String[] matrixValues = inputString.substring(beginIndex, endIndex).split(",");
            svgTransform = new SvgTransform(Double.valueOf(matrixValues[0]), Double.valueOf(matrixValues[3]),
                    Double.valueOf(matrixValues[4]), Double.valueOf(matrixValues[5]),
                    Double.valueOf(matrixValues[1]), Double.valueOf(matrixValues[2]));
            svgTransform.setMatrix(true);

            return svgTransform;
        }
        else {
            //todo
        }

        return svgTransform;
    }

    private StyleElement parseStyle(String inputString) {
        StyleElement styleElement = new StyleElement();
        String[] parts = inputString.split(";");
        for(String part: parts) {
            String[] pairKV = part.split(":");

            switch (pairKV[0]) {
                case "opacity":
                    styleElement.setOpacity(Integer.valueOf(pairKV[1]));
                    break;
                case "fill":
                    styleElement.setFill(pairKV[1]);
                    break;
                case "fill-opacity":
                    styleElement.setFillOpacity(Integer.valueOf(pairKV[1]));
                    break;
                case "stroke":
                    styleElement.setStroke(pairKV[1]);
                    break;
                case "stroke-width":
                    styleElement.setStrokeWidth(Double.valueOf(pairKV[1]));
                    break;
                case "stroke-linecap":
                    styleElement.setStrokeLinecap(pairKV[1]);
                    break;
                case "stroke-linejoin":
                    styleElement.setStrokeLineJoin(pairKV[1]);
                    break;
                case "stroke-miterlimit":
                    styleElement.setStrokeMiterlimit(Integer.valueOf(pairKV[1]));
                    break;
                case "stroke-dasharray":
                    styleElement.setStrokeDashArray(pairKV[1]);
                    break;
                case "stroke-dashoffset":
                    styleElement.setStrokeDashOffset(Integer.valueOf(pairKV[1]));
                    break;
                case "stroke-opacity":
                    styleElement.setStrokeOpacity(Integer.valueOf(pairKV[1]));
                    break;
                case "stop-color":
                    styleElement.setStopColor(pairKV[1]);
                    break;
                case "stop-opacity":
                    styleElement.setStopOpacity(Integer.valueOf(pairKV[1]));
            }
        }

        return styleElement;
    }




}
