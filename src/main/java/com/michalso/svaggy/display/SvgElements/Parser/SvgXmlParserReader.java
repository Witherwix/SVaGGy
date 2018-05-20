package com.michalso.svaggy.display.SvgElements.Parser;

import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;
import com.michalso.svaggy.display.Utils.SvgUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class SvgXmlParserReader implements SvgParserReader {
    @Override
    public void addTag(String tag) {
        this.tag = tag;
    }

    @Override
    public void addElement(String name, SvgElement svg) {
        if (attrs.length() == 0) {
            attrs += name + "=" + svg.getSvgStringWithoutTag();
        } else {
            if ((attrs.charAt(attrs.length() - 1) != ' ') && (attrs.charAt(attrs.length() - 1) != '<')) {
                attrs +=" " + name + "=" + svg.getSvgStringWithoutTag();
            }

        }
    }

    @Override
    public void addElement(String name, Object obj) {
        if (attrs.length() == 0) {
            attrs += name + "=" + SvgUtils.quoteValue(String.valueOf(obj));
        } else {
            if ((attrs.charAt(attrs.length() - 1) != ' ') && (attrs.charAt(attrs.length() - 1) != '<')) {
                attrs += " " + name + "=" + SvgUtils.quoteValue(String.valueOf(obj));
            }
        }
    }

    @Override
    public void addElement(String name, String obj) {
        if (attrs.length() == 0) {
            attrs += name + "=" + SvgUtils.quoteValue(obj);
        } else {
            if ((attrs.charAt(attrs.length() - 1) != ' ') && (attrs.charAt(attrs.length() - 1) != '<')) {
                attrs += " " + name + "=" + SvgUtils.quoteValue(obj);
            }
        }
    }

    @Override
    public void addElement(String name, Optional<? extends SvgElement> svg) {

        if (svg.isPresent()) {
            if (attrs.length() == 0) {
                attrs += name + "=" + svg.get().getSvgStringWithoutTag();
            } else {
                if ((attrs.charAt(attrs.length() - 1) != ' ') && (attrs.charAt(attrs.length() - 1) != '<')) {
                    attrs += " " + name + "=" + svg.get().getSvgStringWithoutTag();
                }
            }
        }
    }

    @Override
    public void addElement(Optional<? extends SvgElement> svg) {
        if (svg.isPresent()) {
            tags.add(svg.get().getSvgString());
        }
    }

    @Override
    public void addElement(SvgElement svg) {
        tags.add(svg.getSvgString());
    }

    //todo wrong
    @Override
    public void addElements(List<String> names, List<? extends SvgElement> svgs) {
        StringJoiner joiner = new StringJoiner(" ");
        IntStream.range(0, names.size()).forEach(i -> joiner.add(names.get(i) + "=" + svgs.get(i).getSvgString()));
        attrs += joiner.toString();
    }

    @Override
    public void addElements(List<? extends SvgElement> svgs) {
        IntStream.range(0, svgs.size()).forEach(i -> tags.add(svgs.get(i).getSvgString()));
    }

    @Override
    public void addElementsObj(List<String> names, List<Object> objs) {
        StringJoiner joiner = new StringJoiner(" ");
        IntStream.range(0, names.size()).forEach(i ->
                joiner.add(names.get(i) + "=" + SvgUtils.quoteValue(String.valueOf(objs.get(i)))));

        if (joiner.length() != 0)
        {
            if (attrs.length() != 0 && ((attrs.charAt(attrs.length() - 1) != ' ') && (attrs.charAt(attrs.length() - 1) != '<')))
            {
                attrs += " ";
            }
        }

        attrs += joiner.toString();
    }

    @Override
    public void addElementsOptional(Map<String, Optional<? extends SvgElement>> elms) {
        for (Map.Entry<String, Optional<? extends SvgElement>> entry : elms.entrySet()) {
            if (entry.getValue().isPresent()) {
                addElement(entry.getKey(), entry.getValue().get());
            }
        }
    }

    @Override
    public void addElementsOptionalObj(Map<String, Optional<? extends Object>> elms) {
        for (Map.Entry<String, Optional<? extends Object>> entry : elms.entrySet()) {
            if (entry.getValue().isPresent()) {
                addElement(entry.getKey(), entry.getValue().get());
            }
        }
    }

    @Override
    public void addAsSingleElementOptionalObj( Map<String, Optional<? extends Object>> objs)
    {
        StringJoiner joiner = new StringJoiner(";");
        for (Map.Entry<String, Optional<? extends Object>> entry : objs.entrySet()) {
            if (entry.getValue().isPresent()) {
                joiner.add(entry.getKey() + ":" + entry.getValue().get());
            }
        }

        addElement(joiner.toString());
    }

    @Override
    public String getString() {
        if (tag.length() != 0) {

            if (attrs.length() != 0) {
                if (tags.length() != 0) {
                    return "<" + tag + " " + attrs + ">" + "\n" + tags.toString() + "\n" + "</" + tag + ">";
                } else {
                    return "<" + tag + " " + attrs + "/>";
                }
            } else {
                if (tags.length() != 0) {
                    return "<" + tag +">" + "\n" + tags.toString() +"\n" + "</" + tag + ">";
                } else  {
                    return "<" + tag + "/>" ;
                }
            }
        } else {
            if (attrs.length() != 0) {
                if (tags.length() != 0) {
                    return attrs  + "\n" + tags.toString() + "\n" + "</" + tag + ">";
                } else {
                    return attrs;
                }
            } else {
                if (tags.length() != 0) {
                    return  tags.toString() + "\n" + "</" + tag + ">";
                } else  {
                    return  tag ;
                }
            }
        }
    }

    private void addElement(Object obj) {
        if (attrs.length() == 0) {
            attrs = SvgUtils.quoteValue(obj);
        } else {
            if ((attrs.charAt(attrs.length() - 1) != ' ') && (attrs.charAt(attrs.length() - 1) != '<')) {
                attrs += " " + SvgUtils.quoteValue(obj);
            }
        }
    }

    private String tag = "";
    private String attrs = "";
    private StringJoiner tags = new StringJoiner("\n");

}
