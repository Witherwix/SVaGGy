package com.michalso.svaggy.display.SvgElements.Bezier;

import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;
import com.michalso.svaggy.display.SvgElements.StyleParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
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

public class BezierParser {

    public BezierPath parse(String string) throws IOException, SAXException, ParserConfigurationException {
        BezierPath bezPath = new BezierPath();
        //parse xml and depending on tag do some actions
        DocumentBuilder dbBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(string));
        Document doc = dbBuilder.parse(is);
        for (int i=0; i <doc.getChildNodes().getLength(); i++) {
            Node node = doc.getChildNodes().item(i);

            NamedNodeMap mapNodeAttrs= node.getAttributes();

            for (int j=0; j< mapNodeAttrs.getLength(); j++) {
                Node nodeAttr = mapNodeAttrs.item(j);

                if (nodeAttr.getNodeName().equals("d")) {
                    bezPath.setFragments(parseFragments(nodeAttr.getNodeValue()));
                }
                else if (nodeAttr.getNodeName().equals("style")) {
                    StyleElement styleElement = new StyleElement();
                    StyleParser styleParser = new StyleParser();
                    styleParser.parse(styleElement, nodeAttr.getNodeValue());
                    bezPath.setStyleElement(styleElement);
                }
            }
        }
        return bezPath;
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

    /*
        return increased index containing information how many parts were processed
     */
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
}
