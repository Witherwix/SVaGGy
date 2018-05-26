package Manipulators;

import com.michalso.svaggy.display.SvgElements.Basic.SvgGroup;
import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import com.michalso.svaggy.display.SvgElements.Bezier.BezierPath;
import com.michalso.svaggy.display.Generators.Manipulators.SvgPartExtractor;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestPartExtractor {

    @Test
    public void testGetChildrenGroupPath() {
        SvgRoot root = new SvgRoot();
        root.addGroup(new SvgGroup());
        SvgGroup group = new SvgGroup();
        group.addElement(new BezierPath());
        group.addElement(new BezierPath());
        group.addElement(new BezierPath());
        root.addGroup(group);

        System.out.println(group.getChildren());
        SvgPartExtractor extractor = new SvgPartExtractor(root);
        List<BezierPath> paths = extractor.getAllElements(BezierPath.class);
        List<SvgGroup> groups= extractor.getAllElements(SvgGroup.class);

        assertEquals(2, groups.size());
        assertEquals(3, paths.size());
    }
}
