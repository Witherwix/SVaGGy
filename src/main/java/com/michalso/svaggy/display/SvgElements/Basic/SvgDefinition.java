package com.michalso.svaggy.display.SvgElements.Basic;

import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;

import java.util.ArrayList;
import java.util.List;


public class SvgDefinition extends SvgElement {

    private List<SvgElement> elements = new ArrayList<>();

    @Override
    public String getSvgString() {
        SvgParserReader svgParser = new SvgXmlParserReader();
        svgParser.addTag("defs");
        super.addElementsToParse(svgParser);
        svgParser.addElements(elements);
        return svgParser.getString();
    }

    @Override
    public List<SvgElement> getChildren() {
        List<SvgElement> children = super.getChildren();
        children.addAll(elements);

        return children;
    }

    @Override
    public SvgDefinition cloneObject() {
        SvgDefinition cloneDef = new SvgDefinition();
        cloneDef.cloneSvgElements(this);
        for (SvgElement elm : elements) {
            cloneDef.addElement(elm.cloneObject());
        }

        return cloneDef;
    }

    public List<SvgElement> getElements() {
        return elements;
    }

    public void setElements(List<SvgElement> elements) {
        this.elements = elements;
    }

    public void addElement(SvgElement elem) {
        this.elements.add(elem);
    }
}
