package com.michalso.svaggy.display.SvgElements.Basic;

import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SvgGroup extends SvgElement implements Boundable {


    private List<SvgElement> elements = new ArrayList<>();

    @Override
    public String getSvgString() {
        SvgParserReader parser = new SvgXmlParserReader();
        parser.addTag("g");
        super.addElementsToParse(parser);
        parser.addElements(elements);

        return parser.getString();
    }

    @Override
    public List<SvgElement> getChildren() {
        List<SvgElement> children = super.getChildren();
        children.addAll(elements);

        return children;
    }

    @Override
    public BoundingBox getBoundingBox() {
        List<Boundable> boundables =  elements.stream().filter(e -> e instanceof Boundable).map(e -> (Boundable)e).collect(Collectors.toList());
        return BoundingBox.mergeBoundingBoxes(boundables.stream().map(e -> e.getBoundingBox()).collect(Collectors.toList()));
    }

    @Override
    public SvgGroup cloneObject() {
        SvgGroup cloneGroup = new SvgGroup();
        super.cloneSvgElements(cloneGroup);

        for(SvgElement elm : elements) {
            cloneGroup.addElement(elm.cloneObject());
        }

        return cloneGroup;
    }

    public void addElement(SvgElement elem) {
        elements.add(elem);
    }

    public List<SvgElement> getElements() {
        return elements;
    }

    public void setElements(List<SvgElement> elements) {
        this.elements = elements;
    }


}
