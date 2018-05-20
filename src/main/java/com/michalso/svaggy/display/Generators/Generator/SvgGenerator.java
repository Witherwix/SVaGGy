package com.michalso.svaggy.display.Generators.Generator;

import com.michalso.svaggy.display.Generators.Manipulators.BasicBezierFragmentManipulator;
import com.michalso.svaggy.display.Generators.Manipulators.BezierFragmentManipulator;
import com.michalso.svaggy.display.SvgElements.Basic.SvgCircle;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class SvgGenerator {

    public List<SvgCircle> generateCircles(SvgCircle orgCircle, int numCopies){
        Random rand = new Random();

        List<SvgCircle> circles = new ArrayList<>();
        for (int i=0; i<numCopies; i++) {
            SvgCircle newCircle = new SvgCircle(orgCircle);

            /*int max = 50;
            int min = -50;
            int num = rand.nextInt(max + 1 - min) + min;*/

            int max = 20;
            int min = -20;
            int num = rand.nextInt(max + 1 - min) + min;

            //todo cahnge
            /*newCircle.setCx(newCircle.getCx() + num);
            newCircle.setCy(newCircle.getCy() + num);*/
            newCircle.setR(newCircle.getR() + num);
            circles.add(newCircle);
        }
        return circles;
    }

    /*public List<BezierPath> generatePaths(BezierPath bezierPath, int numCopies) {
        List<BezierPath> bezierPaths = new ArrayList<>();

        Random rand = new Random();
        for (int i=0; i<numCopies; i++) {
            BezierPath newBezierPath = new BezierPath(bezierPath);

            manipulatePoints(newBezierPath);

            if (bezierPath.getStyleElement().isPresent()) {
                StyleManipulator styleManipulator = new StyleManipulator(bezierPath.getStyleElement().get());
                styleManipulator.changeFill(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            }

            bezierPaths.add(newBezierPath);
        }

        return bezierPaths;
    }*/

    public List<SvgRoot> generateCopies( int numCopies, SvgRoot root) {
        ArrayList<SvgRoot> roots = new ArrayList<>();
        for (int i=0; i<numCopies; i++) {
            SvgRoot cloneRoot = root.cloneObject();
            roots.add(cloneRoot);
        }

        return roots;
    }

}
