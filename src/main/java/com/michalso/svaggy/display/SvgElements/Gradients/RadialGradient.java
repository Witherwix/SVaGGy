package com.michalso.svaggy.display.SvgElements.Gradients;

import com.michalso.svaggy.display.SvgElements.Basic.SvgElement;
import com.michalso.svaggy.display.SvgElements.Parser.SvgParserReader;
import com.michalso.svaggy.display.SvgElements.Parser.SvgXmlParserReader;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class RadialGradient extends SvgGradient {

    Optional<Integer> cx = Optional.empty();
    Optional<Integer> cy = Optional.empty();
    Optional<Integer> r = Optional.empty();
    Optional<Integer> fx = Optional.empty();
    Optional<Integer> fy = Optional.empty();
    Optional<Integer> fr = Optional.empty();

    @Override
    public String getSvgString() {
        SvgParserReader parser = new SvgXmlParserReader();
        parser.addTag("RadialGradient");
        super.addElementsToParse(parser);

        Map<String, Optional<? extends Object>> mapObj = new LinkedHashMap<>();
        mapObj.put("cx", cx);
        mapObj.put("cy", cy);
        mapObj.put("r", r);
        mapObj.put("fx", fx);
        mapObj.put("fy", fy);
        mapObj.put("fr", fr);

        parser.addElementsOptionalObj(mapObj);
        parser.addElements(stopElements);
        return parser.getString();
    }

    @Override
    public RadialGradient cloneObject() {
        RadialGradient cloneGradient = new RadialGradient();
        cloneGradient.cx = cx;
        cloneGradient.cy = cy;
        cloneGradient.r = r;
        cloneGradient.fx = fx;
        cloneGradient.fy = fy;
        cloneGradient.fr = fr;
        super.cloneSvgElements(cloneGradient);

        return cloneGradient;
    }


    public Optional<Integer> getCx() {
        return cx;
    }

    public void setCx(Integer cx) {
        this.cx = Optional.of(cx);
    }

    public Optional<Integer> getCy() {
        return cy;
    }

    public void setCy(Integer cy) {
        this.cy = Optional.of(cy);
    }

    public Optional<Integer> getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = Optional.of(r);
    }

    public Optional<Integer> getFx() {
        return fx;
    }

    public void setFx(Integer fx) {
        this.fx = Optional.of(fx);
    }

    public Optional<Integer> getFy() {
        return fy;
    }

    public void setFy(Integer fy) {
        this.fy = Optional.of(fy);
    }

    public Optional<Integer> getFr() {
        return fr;
    }

    public void setFr(Integer fr) {
        this.fr = Optional.of(fr);
    }
}
