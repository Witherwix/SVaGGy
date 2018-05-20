package com.michalso.svaggy.display.Generators.Manipulators;

import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SvgPartExtractor {
    public SvgPartExtractor(SvgRoot svg) {
        this.root = svg;
    }

    private SvgRoot root;

    public <T extends SvgElement> List<T> getAllElements(Class svgClass) {
        List<SvgElement> allElements = new ArrayList<>();
        allElements = getAllElements(root);
        List<SvgElement> elems =  allElements.stream().filter(e-> e.getClass().equals(svgClass)).collect(Collectors.toList());
        List<T> retElems = new ArrayList<>();
        elems.forEach(e -> retElems.add((T) e));

        return retElems;
    }

    public List<SvgElement> getAllElements(SvgElement svgElem) {
        List<SvgElement> svgs = new ArrayList<>();
        svgs.addAll(svgElem.getChildren());
        for(SvgElement elem : svgElem.getChildren()) {
            svgs.addAll(getAllElements(elem));
        }

        return svgs;
    }

}
