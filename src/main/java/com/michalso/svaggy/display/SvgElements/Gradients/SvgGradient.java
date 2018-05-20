package com.michalso.svaggy.display.SvgElements.Gradients;

import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;

import java.util.ArrayList;
import java.util.List;

public abstract class SvgGradient extends SvgElement {

    @Override
    public List<SvgElement> getChildren() {
        List<SvgElement> children = super.getChildren();
        children.addAll(stopElements);

        return children;
    }

    protected List<SvgStopElement> stopElements = new ArrayList<>();

    public void addStopElement(SvgStopElement stopElem) {
        stopElements.add(stopElem);
    }

    public List<SvgStopElement> getStopElements() {
        return stopElements;
    }

    public void setStopElements(List<SvgStopElement> stopElements) {
        this.stopElements = stopElements;
    }
}
