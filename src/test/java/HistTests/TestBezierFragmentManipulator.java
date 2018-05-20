package HistTests;

import static org.junit.Assert.assertEquals;

public class TestBezierFragmentManipulator {


   /* @Test
    public void testConvertLineToCubic() {
        BezierFragment fragment = new BezierFragment("l", List.of(new Point2D.Double(1,1)));
        BasicBezierFragmentManipulator manipulator = new BasicBezierFragmentManipulator(fragment);

        manipulator.convertLineToCubic(new Point2D.Double(4,4), new Point2D.Double(10, 10));

        assertEquals(fragment.getType(), "c");
        assertEquals(fragment.getPoints().get(0), new Point2D.Double(4,4));
        assertEquals(fragment.getPoints().get(2), new Point2D.Double(1,1));
    }

    @Test(expected = RuntimeException.class)
    public void testConvertLineToCubicWithWrongType() {
        BezierFragment fragment = new BezierFragment("C", List.of(new Point2D.Double(1,1)));
        BasicBezierFragmentManipulator manipulator = new BasicBezierFragmentManipulator(fragment);

        manipulator.convertLineToCubic(new Point2D.Double(4,4), new Point2D.Double(10, 10));
    }*/
}
