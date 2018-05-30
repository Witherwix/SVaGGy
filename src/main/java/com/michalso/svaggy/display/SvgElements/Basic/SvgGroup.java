package com.michalso.svaggy.display.SvgElements.Basic;

import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;

import java.awt.geom.Point2D;
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
        BoundingBox box= BoundingBox.mergeBoundingBoxes(boundables.stream().map(e -> e.getBoundingBox()).collect(Collectors.toList()));

        if (transform.isPresent()) {
            return box.getScaledBox(transform.get().getScale());
        } else {
            return box;
        }
    }

    @Override
    public void move(Point2D moveOffset) {
        //move transform by appropriate pixel numbers, as during scaling local coordinate system changes
        if (transform.isPresent()) {
            //transform.get().move(moveOffset);
            transform.get().clearTranslation();
        }

        for( SvgElement e :elements) {
            System.out.println(e);
        }
        elements.stream().filter(e -> e instanceof Boundable).forEach(e -> ((Boundable)e).move(moveOffset));
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
