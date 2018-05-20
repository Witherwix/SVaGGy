package com.michalso.svaggy.display.Generators.Manipulators;

import com.google.common.base.Splitter;
import com.michalso.svaggy.display.SvgElements.Basic.StyleElement;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;
import com.michalso.svaggy.display.Utils.ManipUtils;

import javax.swing.text.Style;
import java.awt.*;
import java.util.List;
import java.util.Random;


public class BasicStyleManipulator implements StyleManipulator {

    @Override
    public void changeFill(int fromOffset, int toOffset, SvgRoot root) {
        SvgPartExtractor partExtractor = new SvgPartExtractor(root);
        List<StyleElement> allStyles = partExtractor.getAllElements(StyleElement.class);

        allStyles.forEach(e -> changeFill(fromOffset, toOffset, e));
    }

    public void changeFill(int fromOffset, int toOffset, StyleElement styleElement) {
        String strColor = styleElement.getFill().get();
        List<String> colorParts = Splitter.fixedLength(2).splitToList(strColor.substring(1));

        Random rand = new Random();
        int rOffset = ManipUtils.getRandom(rand, fromOffset, toOffset);
        int gOffset = ManipUtils.getRandom(rand, fromOffset, toOffset);
        int bOffset = ManipUtils.getRandom(rand, fromOffset, toOffset);

        try {

            int valR = (Integer.parseInt(colorParts.get(0), 16) + rOffset);
            if(valR > 255) {
                valR = 255;
            }

            int valG = (Integer.parseInt(colorParts.get(1), 16) + gOffset);
            if(valG > 255) {
                valG = 255;
            }

            int valB = (Integer.parseInt(colorParts.get(2), 16) + bOffset);
            if(valB > 255) {
                valB = 255;
            }

            Color color = new Color(valR, valG,
                    valB);

            strColor = colorToHashString(color);

            styleElement.setFill(strColor);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private String colorToHashString(Color color) {
        String strColor ="#" + Integer.toHexString(color.getRed()) + Integer.toHexString(color.getGreen()) +
                Integer.toHexString(color.getBlue());

        return strColor;
    }

}
