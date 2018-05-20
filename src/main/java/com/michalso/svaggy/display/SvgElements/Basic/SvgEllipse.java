package com.michalso.svaggy.display.SvgElements.Basic;

import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;

import java.util.List;

public class SvgEllipse extends SvgElement {

    public SvgEllipse() {

    }

    public SvgEllipse(double cx, double cy, double rx, double ry) {
        this.cx = cx;
        this.cy = cy;
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public String getSvgString() {
        SvgXmlParserReader parser = new SvgXmlParserReader();
        parser.addTag("ellipse");
        super.addElementsToParse(parser);
        parser.addElementsObj(List.of("cx", "cy", "rx", "ry"), List.of(cx, cy, rx, ry));

        return parser.getString();
    }

    @Override
    public SvgEllipse cloneObject() {
        SvgEllipse svgEllipse = new SvgEllipse(cx, cy, rx, ry);
        super.cloneSvgElements(svgEllipse);

        return svgEllipse;
    }


    private double cx;
    private double cy;
    private double rx;
    private double ry;

    public double getCx() {
        return cx;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public double getRx() {
        return rx;
    }

    public void setRx(double rx) {
        this.rx = rx;
    }

    public double getRy() {
        return ry;
    }

    public void setRy(double ry) {
        this.ry = ry;
    }
}
