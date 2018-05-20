package com.michalso.svaggy.display.SvgElements.Basic;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class SvgElement {
    protected Optional<String> id = Optional.empty();
    protected Optional<StyleElement> styleElement = Optional.empty(); //todo move to otehr class?
    protected Optional<String> transform = Optional.empty(); //todo cahnge to sepeerate class
    //protected Optional<String> rawInfo = Optional.empty();

    abstract public String getSvgString();
    public String getSvgStringWithoutTag() {
        return "";
    }; //some elements might be both attributes, and tags

    protected void addElementsToParse(SvgParserReader parser) {
        if(id.isPresent()) {
            parser.addElement("id", id.get());
        }

        if (transform.isPresent()) {
            parser.addElement("transform", transform.get());
        }

        parser.addElement("style", styleElement);
    }

    public List<SvgElement> getChildren() {
        List<SvgElement> children= new ArrayList<>();

        if (styleElement.isPresent()) {
            children.add(styleElement.get());
        }

        return children;
    }

    public abstract SvgElement cloneObject();

    public void cloneSvgElements(SvgElement elm) {
        elm.id = id;
        if (styleElement.isPresent()) {
            elm.styleElement = Optional.of(styleElement.get().cloneObject());
        }

        if(transform.isPresent()) {
            elm.transform = transform;
        }
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Optional.of(id);
    }

    public Optional<StyleElement> getStyleElement() {
        return styleElement;
    }

    public void setStyleElement(StyleElement style) {
        this.styleElement = Optional.of(style);
    }

    public Optional<String> getTransform() {
        return transform;
    }

    public void setTransform(String transform) {
        this.transform = Optional.of(transform);
    }
}



