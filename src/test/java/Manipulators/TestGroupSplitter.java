package Manipulators;

import com.michalso.svaggy.display.Generators.Manipulators.GroupSplitter;
import com.michalso.svaggy.display.SvgElements.Basic.*;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGroupSplitter {

    @Test
    public void wip() {
        SvgEllipse elip = new SvgEllipse();

        if (SvgElement.class.isInstance(elip)) {
            System.out.println("TRue");
        } else {
            System.out.println("False");
        }
    }

    @Test
    public void testSplit2EllipsesInOneGroup() {
        SvgRoot root =  new SvgRoot();
        SvgGroup group1 = new SvgGroup();
        SvgEllipse ellipse1 = new SvgEllipse(300, 300, 10, 20);
        SvgEllipse ellipse2 = new SvgEllipse(400, 400, 10, 20);
        group1.addElement(ellipse1);
        group1.addElement(ellipse2);

        root.addGroup(group1);

        GroupSplitter splitter = new GroupSplitter();
        List<SvgRoot> roots = splitter.splitGroupsToSvg(root);

        List<SvgGroup> groups = roots.get(0).getGroups();
        List<SvgEllipse> ellipses = (List<SvgEllipse>) ((List<?>)groups.get(0).getElements());

        assertEquals(10, ellipses.get(0).getCx(), 0);
        assertEquals(20, ellipses.get(0).getCy(), 0);
        assertEquals(110, ellipses.get(1).getCx(), 0);
        assertEquals(120, ellipses.get(1).getCy(), 0);
    }
}
