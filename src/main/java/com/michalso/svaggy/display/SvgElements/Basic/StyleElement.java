package com.michalso.svaggy.display.SvgElements.Basic;

import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;
import com.michalso.svaggy.display.SvgElements.StyleParser;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class StyleElement extends SvgElement {

    private Optional<Integer> opacity = Optional.empty();
    private Optional<String> fill = Optional.empty();
    private Optional<Integer> fillOpacity = Optional.empty();
    private Optional<String> stroke = Optional.empty();
    private Optional<Double> strokeWidth = Optional.empty();
    private Optional<String> strokeLinecap = Optional.empty();
    private Optional<String> strokeLineJoin = Optional.empty();
    private Optional<Integer> strokeMiterlimit = Optional.empty();
    private Optional<String> strokeDashArray = Optional.empty();
    private Optional<Integer> strokeDashOffset = Optional.empty();
    private Optional<Integer> strokeOpacity = Optional.empty();
    private Optional<String> stopColor = Optional.empty();
    private Optional<Integer> stopOpacity = Optional.empty();

    public StyleElement() {

    }

    public StyleElement(StyleElement elem) {
        this.opacity = elem.opacity;
        this.fill = elem.fill;
        this.fillOpacity = elem.fillOpacity;
        this.stroke = elem.stroke;
        this.strokeWidth = elem.strokeWidth;
        this.strokeLinecap = elem.strokeLinecap;
        this.strokeLineJoin = elem.strokeLineJoin;
        this.strokeMiterlimit = elem.strokeMiterlimit;
        this.strokeDashArray = elem.strokeDashArray;
        this.strokeDashOffset = elem.strokeDashOffset;
        this.strokeOpacity = elem.strokeOpacity;
    }

    public StyleElement(String inputString) {
        fromSvgString(inputString);
    }

    @Override
    public StyleElement cloneObject() {
        StyleElement elem = new StyleElement(this);
        super.cloneSvgElements(elem);

        return elem;
    }

    public void fromSvgString(String inputString) {
        StyleParser styleParser = new StyleParser();
        styleParser.parse(this, inputString);
    }

    @Override
    public String getSvgString() {
        SvgParserReader parser = constructParser();
        parser.addTag("style");
        return parser.getString();
    }

    @Override
    public String getSvgStringWithoutTag() {
        SvgParserReader parser = constructParser();
        return parser.getString();
    }

    public SvgParserReader constructParser() {
        SvgParserReader parser = new SvgXmlParserReader();
        super.addElementsToParse(parser);

        Map<String, Optional<? extends Object>> map = new LinkedHashMap<>();
        map.put("opacity", opacity);
        map.put("fill", fill);
        map.put("fill-opacity", fillOpacity);
        map.put("stroke", stroke);
        map.put("stroke-width", strokeWidth);
        map.put("stroke-linecap", strokeLinecap);
        map.put("stroke-linejoin", strokeLineJoin);
        map.put("stroke-miterlimit", strokeMiterlimit);
        map.put("stroke-dasharray", strokeDashArray);
        map.put("stroke-dashoffset", strokeDashOffset);
        map.put("stroke-opacity", strokeOpacity);
        map.put("stop-color", stopColor);
        map.put("stop-opacity", stopOpacity);
        parser.addAsSingleElementOptionalObj( map);

        return parser;
    }

    public Optional<Integer> getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        this.opacity = Optional.of(opacity);
    }

    public Optional<String> getFill() {
        return fill;
    }

    public void setFill(String fill) { this.fill = Optional.of(fill); }


    public Optional<String> getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = Optional.of(stroke);
    }

    public Optional<Double> getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Double strokeWidth) {
        this.strokeWidth = Optional.of(strokeWidth);
    }

    public Optional<String> getStrokeLinecap() {
        return strokeLinecap;
    }

    public void setStrokeLinecap(String strokeLinecap) {
        this.strokeLinecap = Optional.of(strokeLinecap);
    }

    public Optional<String> getStrokeLineJoin() {
        return strokeLineJoin;
    }

    public void setStrokeLineJoin(String strokeLineJoin) {
        this.strokeLineJoin = Optional.of(strokeLineJoin);
    }

    public Optional<Integer> getStrokeMiterlimit() {
        return strokeMiterlimit;
    }

    public void setStrokeMiterlimit(Integer strokeMiterlimit) {
        this.strokeMiterlimit = Optional.of(strokeMiterlimit);
    }

    public Optional<Integer> getStrokeDashOffset() {
        return strokeDashOffset;
    }

    public void setStrokeDashOffset(Integer strokeDashOffset) {
        this.strokeDashOffset = Optional.of(strokeDashOffset);
    }

    public Optional<Integer> getStrokeOpacity() {
        return strokeOpacity;
    }

    public void setStrokeOpacity(Integer strokeOpacity) {
        this.strokeOpacity = Optional.of(strokeOpacity);
    }

    public Optional<String> getStrokeDashArray() {
        return strokeDashArray;
    }

    public void setStrokeDashArray(String strokeDashArray) { this.strokeDashArray = Optional.of(strokeDashArray); }

    public Optional<Integer> getFillOpacity() {
        return fillOpacity;
    }

    public void setFillOpacity(Integer fillOpacity) {
        this.fillOpacity = Optional.of(fillOpacity);
    }

    public Optional<String> getStopColor() {
        return stopColor;
    }

    public void setStopColor(String stopColor) {
        this.stopColor = Optional.of(stopColor);
    }

    public Optional<Integer> getStopOpacity() {
        return stopOpacity;
    }

    public void setStopOpacity(Integer stopOpacity) {
        this.stopOpacity = Optional.of(stopOpacity);
    }
}
