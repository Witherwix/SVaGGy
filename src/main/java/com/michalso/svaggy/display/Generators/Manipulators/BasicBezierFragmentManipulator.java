package com.michalso.svaggy.display.Generators.Manipulators;

import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierFragment;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BasicBezierFragmentManipulator implements BezierFragmentManipulator {

    public BasicBezierFragmentManipulator() {

    }

    @Override
    public void changeControlPoints(int offsetFrom, int offsetTo, SvgRoot root) {
        SvgPartExtractor partExtractor = new SvgPartExtractor(root);
        List<BezierPath> allPaths =partExtractor.getAllElements(BezierPath.class);
        List<BezierFragment> fragments = new ArrayList<>();
        allPaths.forEach(p -> fragments.addAll(p.getFragments()));

        List<BezierFragment> cFragments = fragments.stream().filter(e -> e.getType().toUpperCase().equals("C")).collect(Collectors.toList());

        Random rand = new Random();
        for (BezierFragment frag : cFragments) {
            double p1OffsetX = rand.nextDouble() * (offsetTo - offsetFrom) + offsetFrom;
            double p1OffsetY = rand.nextDouble() * (offsetTo - offsetFrom) + offsetFrom;

            double p2OffsetX = rand.nextDouble() * (offsetTo - offsetFrom) + offsetFrom;
            double p2OffsetY = rand.nextDouble() * (offsetTo - offsetFrom) + offsetFrom;

            System.out.println("Before");
            System.out.println(frag);
            frag.changeAllControlPoints(new Point2D.Double(p1OffsetX, p1OffsetY), new Point2D.Double(p2OffsetX, p2OffsetY));
            System.out.println("After");
            System.out.println(frag);
        }
    }

}
