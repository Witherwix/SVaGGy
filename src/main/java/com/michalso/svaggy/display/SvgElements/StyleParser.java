package com.michalso.svaggy.display.SvgElements;

import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;

public class StyleParser {

    public void parse(StyleElement styleElement, String inputString) {
        String[] parts = inputString.split(";");
        for(String part: parts) {
            String[] pairKV = part.split(":");

           /* switch (pairKV[0]) {
                case "opacity":
                    styleElement.setOpacity(Optional.of(Integer.valueOf(pairKV[1])));
                    break;
                case "fill":
                    styleElement.setFill(Optional.of(pairKV[1]));
                    break;
                case "fill-opacity":
                    styleElement.setFillOpacity(Optional.of(Integer.valueOf(pairKV[1])));
                    break;
                case "stroke":
                    styleElement.setStroke(Optional.of(pairKV[1]));
                    break;
                case "stroke-width":
                    styleElement.setStrokeWidth(Optional.of(Double.valueOf(pairKV[1])));
                    break;
                case "stroke-linecap":
                    styleElement.setStrokeLinecap(Optional.of(pairKV[1]));
                    break;
                case "stroke-linejoin":
                    styleElement.setStrokeLineJoin(Optional.of(pairKV[1]));
                    break;
                case "stroke-miterlimit":
                    styleElement.setStrokeMiterlimit(Optional.of(Integer.valueOf(pairKV[1])));
                    break;
                case "stroke-dasharray":
                    styleElement.setStrokeDashArray(Optional.of(pairKV[1]));
                    break;
                case "stroke-dashoffset":
                    styleElement.setStrokeDashOffset(Optional.of(Integer.valueOf(pairKV[1])));
                    break;
                case "stroke-opacity":
                    styleElement.setStrokeOpacity(Optional.of(Integer.valueOf(pairKV[1])));
                    break;

            }*/

        }
    }
}
