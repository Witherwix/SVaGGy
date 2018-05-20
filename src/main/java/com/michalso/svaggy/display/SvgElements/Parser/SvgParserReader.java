package com.michalso.svaggy.display.SvgElements.Parser;

import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SvgParserReader {
    void addTag(String tag);

    void addElement(String name, SvgElement svg);
    void addElement(String name, Object obj);
    void addElement(String name, String obj);
    void addElement(String name, Optional<? extends SvgElement> svg);
    void addElement(Optional<? extends SvgElement> svg);
    void addElement(SvgElement svg);
    void addElements(List<String> names, List<? extends SvgElement> svgs);
    void addElements(List<? extends SvgElement> svgs);
    void addElementsObj(List<String> names, List<Object> objs);
    void addElementsOptional(Map<String, Optional<? extends SvgElement>> elms);
    void addElementsOptionalObj(Map<String, Optional<? extends Object>> elms);
    void addAsSingleElementOptionalObj(Map<String, Optional<? extends Object>> objs);
    String getString();
}
