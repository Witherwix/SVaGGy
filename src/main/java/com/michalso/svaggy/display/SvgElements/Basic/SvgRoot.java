package com.michalso.svaggy.display.SvgElements.Basic;

import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SvgRoot extends  SvgElement {

    private List<SvgGroup> groups = new ArrayList<>();
    private Optional<SvgDefinition> svgDefinition = Optional.empty();
    private int height;
    private int width;

    public SvgRoot() {

    }

    public SvgRoot(SvgRoot root) {
        this(root.width, root.height);
        this.groups = root.groups;
        this.svgDefinition = root.svgDefinition;
    }

    public SvgRoot(int width, int height) {
        this.height = height;
        this.width = width;
    }

    @Override
    public SvgRoot cloneObject() {
        SvgRoot cloneRoot = new SvgRoot(width, height);
        if (svgDefinition.isPresent()) {
            cloneRoot.svgDefinition = Optional.of(svgDefinition.get().cloneObject());
        }
        for(SvgGroup g : groups)
        {
            cloneRoot.addGroup(g.cloneObject());
        }

        return cloneRoot;
    }

    @Override
    public String getSvgString() {
        SvgParserReader parser = new SvgXmlParserReader();
        parser.addTag("svg");
        parser.addElementsObj(List.of("width", "height"), List.of(width, height));
        parser.addElement(svgDefinition);
        parser.addElements(groups);
        return parser.getString();
    }

    @Override
    public List<SvgElement> getChildren() {
        List<SvgElement> children = super.getChildren();
        children.addAll(groups);
        if(svgDefinition.isPresent()) {
            children.add(svgDefinition.get());
        }

        return children;
    }

    public List<SvgGroup> getGroups() {
        return groups;
    }

    public void addGroup(SvgGroup group) {
        groups.add(group);
    }

    public void setGroups(List<SvgGroup> groups) {
        this.groups = groups;
    }

    public Optional<SvgDefinition> getSvgDefinition() {
        return svgDefinition;
    }

    public void setSvgDefinition(SvgDefinition svgDefinition) {
        this.svgDefinition = Optional.of(svgDefinition);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
