package com.michalso.svaggy.display.Generators.Manipulators;

import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SvgPartExtractor {
    public SvgPartExtractor(SvgElement svg) {
        this.root = svg;
    }

    private SvgElement root;

    public <T> List<T> getAllElementsShallow(Class svgClass) {
        List<SvgElement> allElements = getAllElements(root);
        List<SvgElement> elems =  allElements.stream().filter(e-> svgClass.isInstance(e)).collect(Collectors.toList());
        List<T> retElems = new ArrayList<>();
        elems.forEach(e -> retElems.add((T) e));

        return retElems;
    }



    public <T> List<T> getAllElements(Class svgClass) {
        List<SvgElement> allElements = getAllElements(root);
        List<SvgElement> elems =  allElements.stream().filter(e-> svgClass.isInstance(e)).collect(Collectors.toList());
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
