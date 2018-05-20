package com.michalso.svaggy.display.SvgElements.Basic;

import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;

import java.util.List;

public class SvgCircle extends SvgElement {

    private double cx;
    private double cy;
    private double r;
    //private String stroke;
    //private int strokeWidth;
    //private String fill;

    public SvgCircle() {

    }

    public SvgCircle(double cx, double cy, double r) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    public SvgCircle(SvgCircle circle) {
        this.cx = circle.cx;
        this.cy = circle.cy;
        this.r = circle.r;
    }

    @Override
    public String getSvgString() {
        SvgParserReader parser = new SvgXmlParserReader();
        parser.addTag("circle");
        super.addElementsToParse(parser);
        parser.addElementsObj(List.of("cx", "cy", "r"), List.of(cx, cy, r));

        return parser.getString();
    }

    @Override
    public SvgCircle cloneObject() {
        SvgCircle cloneCircle = new SvgCircle(cx, cy, r);
        super.cloneSvgElements(cloneCircle);

        return cloneCircle;
    }


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

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }


}
