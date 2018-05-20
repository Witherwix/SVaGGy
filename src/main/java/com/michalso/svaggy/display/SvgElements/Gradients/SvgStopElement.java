package com.michalso.svaggy.display.SvgElements.Gradients;


import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;
import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;
import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;

import java.util.*;

//offset
//stop-color
//stop-opacity
public class SvgStopElement extends SvgElement {

    private Optional<Integer> offset = Optional.empty();
    private Optional<String> stopColor = Optional.empty();
    private Optional<Integer> stopOpacity = Optional.empty();

    public SvgStopElement() {

    }

    public SvgStopElement(StyleElement styleElement, Integer offset, String stopColor, Integer stopOpacity) {
        this.styleElement = Optional.of(styleElement);
        this.offset = Optional.of(offset);
        this.stopColor = Optional.of(stopColor);
        this.stopOpacity = Optional.of(stopOpacity);
    }

    @Override
    public String getSvgString() {
        SvgParserReader parser = new SvgXmlParserReader();
        parser.addTag("stop");
        super.addElementsToParse(parser);
        Map<String, Optional<? extends Object>> mapObj = new LinkedHashMap<>();
        mapObj.put("offset", offset);
        mapObj.put("stop-color", stopColor);
        mapObj.put("stop-opacity", stopOpacity);
        parser.addElementsOptionalObj(mapObj);

        Map<String, Optional<? extends SvgElement>> mapSvgs = new LinkedHashMap<>();
        parser.addElementsOptional(mapSvgs);

        return  parser.getString();
    }

    @Override
    public SvgStopElement cloneObject() {
        SvgStopElement cloneStop = new SvgStopElement();
        cloneStop.offset = offset;
        cloneStop.stopColor = stopColor;
        cloneStop.stopOpacity = stopOpacity;
        super.cloneSvgElements(cloneStop);

        return cloneStop;
    }

    public Optional<Integer> getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset =  Optional.ofNullable(offset);
    }

    public Optional<String> getStopColor() {
        return stopColor;
    }

    public void setStopColor(String stopColor) {
        this.stopColor = Optional.ofNullable(stopColor);
    }

    public Optional<Integer> getStopOpacity() {
        return stopOpacity;
    }

    public void setStopOpacity(Integer stopOpacity) {
        this.stopOpacity = Optional.ofNullable(stopOpacity);
    }
}
