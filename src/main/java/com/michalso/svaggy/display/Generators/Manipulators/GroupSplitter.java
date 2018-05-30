package com.michalso.svaggy.display.Generators.Manipulators;

import com.michalso.svaggy.display.SvgElements.Basic.Boundable;
import com.michalso.svaggy.display.SvgElements.Basic.BoundingBox;
import com.michalso.svaggy.display.SvgElements.Basic.SvgGroup;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;

public class GroupSplitter {

    private static final double SIZE_OFFSET = 20;

    public List<SvgRoot> splitGroupsToSvg(SvgRoot initSvg) {
        SvgPartExtractor extractor = new SvgPartExtractor(initSvg);
        List<SvgGroup> groups = extractor.getAllElements(SvgGroup.class);
        List<SvgRoot> roots = new ArrayList<>();

        groups = reduceGroupsToSingle(groups);
        for (SvgGroup g : groups) {
            SvgRoot root = new SvgRoot(initSvg.getWidth(), initSvg.getHeight());
            if (initSvg.getSvgDefinition().isPresent()) {
                root.setSvgDefinition(initSvg.getSvgDefinition().get());
            }

            SvgGroup copyGroup = g.cloneObject();
            moveElementsTowardsBegin(copyGroup);
            root.addGroup(copyGroup);

            BoundingBox box = copyGroup.getBoundingBox();
            root.setWidth((int)(box.getWidth() + SIZE_OFFSET));
            root.setHeight((int)(box.getHeight() + SIZE_OFFSET));
            roots.add(root);
        }

        return roots;
    }

    private List<SvgGroup> reduceGroupsToSingle(List<SvgGroup> groups){
        List<SvgGroup> reducGroups = new ArrayList<>();

        for (SvgGroup g: groups) {
            SvgGroup cloneGroup = g.cloneObject();
            reducGroups.add(cloneGroup);
            cloneGroup.setElements(cloneGroup.getElements().stream().filter(e -> !e.getClass().equals(SvgGroup.class)).collect(Collectors.toList()));
        }

        return reducGroups.stream().filter(e -> e.getChildren().size() > 0).collect(Collectors.toList());
    }

    private void moveElementsTowardsBegin(SvgGroup group) {
        SvgPartExtractor extractor = new SvgPartExtractor(group);
        List<Boundable> boundables = extractor.getAllElements(Boundable.class);
        System.out.println(boundables);
        List<BoundingBox> boxes = boundables.stream().filter(Objects::nonNull).map(e ->e.getBoundingBox()).collect(Collectors.toList());

        List<BoundingBox> sortedBoxes = boxes.stream().filter(Objects::nonNull).sorted(Comparator.comparing(e -> e.getLeftUpCorner().getX())).collect(Collectors.toList());
        Point2D moveOffset = BoundingBox.mergeBoundingBoxes(sortedBoxes).getLeftUpCorner();
        //Point2D moveOffset = sortedBoxes.get(0).getLeftUpCorner();

        moveOffset.setLocation(-moveOffset.getX(), -moveOffset.getY());

        group.move(moveOffset);
    }
}
