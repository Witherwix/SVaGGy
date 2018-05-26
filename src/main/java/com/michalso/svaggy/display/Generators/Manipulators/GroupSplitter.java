package com.michalso.svaggy.display.Generators.Manipulators;

import com.michalso.svaggy.display.SvgElements.Basic.BoundingBox;
import com.michalso.svaggy.display.SvgElements.Basic.SvgGroup;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupSplitter {

    public List<SvgRoot> splitGroupsToSvg(SvgRoot initSvg) {
        SvgPartExtractor extractor = new SvgPartExtractor(initSvg);
        List<SvgGroup> groups = extractor.getAllElements(SvgGroup.class);
        List<SvgRoot> roots = new ArrayList<>();

        for (SvgGroup g : groups) {
            SvgRoot root = new SvgRoot(initSvg.getWidth(), initSvg.getHeight());
            if (initSvg.getSvgDefinition().isPresent()) {
                root.setSvgDefinition(initSvg.getSvgDefinition().get());
            }
            root.addGroup(g);
            BoundingBox box = g.getBoundingBox();
            root.setWidth((int)box.getWidth());
            root.setHeight((int)box.getHeight());
            roots.add(root);
        }

        return roots;
    }
}
