package com.michalso.svaggy.display.Generators.Manipulators;

import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;

public interface BezierFragmentManipulator {
    void changeControlPoints(int offsetFrom, int offsetTo, SvgRoot root);
}
