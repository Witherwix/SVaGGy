package com.michalso.svaggy.display.SvgElements.Gradients;

import com.google.common.math.DoubleMath;
import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LinearGradient extends SvgGradient {

    private Optional<Double> x1 = Optional.empty();
    private Optional<Double> y1 = Optional.empty();
    private Optional<Double> x2 = Optional.empty();
    private Optional<Double> y2 = Optional.empty();

    public LinearGradient(Double x1, Double y1, Double x2, Double y2) {
        this.x1 = Optional.of(x1);
        this.y1 = Optional.of(y1);
        this.x2 = Optional.of(x2);
        this.y2 = Optional.of(y2);
    }

    public LinearGradient() {

    }

    @Override
    public String getSvgString() {
        SvgParserReader parser = new SvgXmlParserReader();
        parser.addTag("linearGradient");
        super.addElementsToParse(parser);

        Map<String, Optional<? extends Object>> mapObj = new LinkedHashMap<>();

        List<Optional<? extends Object>> changedList =  List.of(x1, y1, x2, y2).stream().map(val -> {
            if (val.isPresent()) {
                if (DoubleMath.isMathematicalInteger(val.get())) {
                    return Optional.of(val.get().intValue());
                }
            }

            return val;
        }).collect(Collectors.toList());

        mapObj.put("x1", changedList.get(0));
        mapObj.put("y1", changedList.get(1));
        mapObj.put("x2", changedList.get(2));
        mapObj.put("y2", changedList.get(3));



        parser.addElementsOptionalObj(mapObj);
        parser.addElements(stopElements);
        return parser.getString();
    }

    @Override
    public LinearGradient cloneObject() {
        LinearGradient linGradient = new LinearGradient();
        linGradient.x1 = x1;
        linGradient.x2 = x2;
        linGradient.y1 = y1;
        linGradient.y2 = y2;
        super.cloneSvgElements(linGradient);

        return linGradient;
    }

    public Optional<Double> getX1() {
        return x1;
    }

    public void setX1(Double x1) {
        this.x1 = Optional.of(x1);
    }

    public Optional<Double> getY1() {
        return y1;
    }

    public void setY1(Double y1) {
        this.y1 = Optional.of(y1);
    }

    public Optional<Double> getX2() {
        return x2;
    }

    public void setX2(Double x2) {
        this.x2 = Optional.of(x2);
    }

    public Optional<Double> getY2() {
        return y2;
    }

    public void setY2(Double y2) {
        this.y2 = Optional.of(y2);
    }
}
