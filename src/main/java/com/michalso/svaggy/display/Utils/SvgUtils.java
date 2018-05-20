package com.michalso.svaggy.display.Utils;

import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class SvgUtils {


    public static <T> String quoteValue(T val) {
        return "\"" + String.valueOf(val) + "\"";
    }

    public static String quoteValue(String val) {
        return "\"" + val + "\"";
    }

    public static String optStringConcat(List<Optional<?>> opts, List<Optional<SvgElement>> optSvgs, List<Object> objs,
                                         List<SvgElement> svgs) {
        StringJoiner strJoiner = new StringJoiner(" ");

        if (opts != null) {
            opts.stream().filter(Optional::isPresent).map(Optional::get).forEach(e -> strJoiner.add(quoteValue(e)));
        }

        if (optSvgs != null) {
            optSvgs.stream().filter(Optional::isPresent).map(Optional::get).forEach(e -> strJoiner.add(e.getSvgString()));
        }

        if (objs != null) {
            objs.stream().forEach(e -> strJoiner.add(quoteValue(e)));
        }

        if (svgs != null) {
            svgs.stream().forEach(e -> strJoiner.add(e.getSvgString()));
        }

        return strJoiner.toString();
    }

    public static String optStringConcat(List<Object> objs,
                                         List<SvgElement> svgs) {
        StringJoiner strJoiner = new StringJoiner(" ");

        if (objs != null) {
            objs.stream().forEach(e -> strJoiner.add(quoteValue(e)));
        }

        if (svgs != null) {
            svgs.stream().forEach(e -> strJoiner.add(e.getSvgString()));
        }

        return strJoiner.toString();
    }

    public static List<SvgElement> concatListsSvg(List<Optional<?>> optSvg, List<? extends SvgElement> svgs) {
        List<SvgElement> elms = (List<SvgElement>)optSvg.stream().filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());

        elms.addAll(svgs);
        return elms;
    }

    public static List<SvgElement> optSvgToVal(List<Optional<?>> svgs) {
        return (List<SvgElement>)svgs.stream().filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());
    }

    public static List<Object> optObjToVal(List<Optional<? extends Object>> objs) {
        return objs.stream().filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());
    }

    public static List<Object> concatListsObj(List<Optional<Object>> optObj, List<Object> objs) {
        List<Object> elms = optObj.stream().filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());

        elms.addAll(objs);
        return elms;
    }

    public static String optStringConcat(List<Optional<?>> opts, List<Optional<SvgElement>> optSvgs, List<Object> objs) {
        return optStringConcat(opts, optSvgs, objs, null);
    }

    public static String optStringConcat2Svg(List<Optional<SvgElement>> optSvgs, List<Object> objs, List<SvgElement> svgs) {
        return optStringConcat(null, optSvgs, objs, svgs);
    }


}
