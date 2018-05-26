package Basic;

import com.michalso.svaggy.display.SvgElements.Basic.BoundingBox;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class TestBoundingBox {


    @Test
    public void testMergeBoundingBox() {
        BoundingBox box1 = new BoundingBox(-100, 100, -100, 100);
        BoundingBox box2 = new BoundingBox(-200, 200, -50, 120);
        BoundingBox box3 = new BoundingBox(-80, 220, -120, 50);

        BoundingBox retBox = BoundingBox.mergeBoundingBoxes(box1, box2, box3);
        assertEquals(new Point2D.Double(-200, -120), retBox.getLeftDownCorner());
        assertEquals(new Point2D.Double(-200, 120), retBox.getLeftUpCorner());
        assertEquals(new Point2D.Double(220, 120), retBox.getRightUpCorner());
        assertEquals(new Point2D.Double(220, -120), retBox.getRightDownCorner());
    }
}
