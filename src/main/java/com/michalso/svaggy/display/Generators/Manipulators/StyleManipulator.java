package com.michalso.svaggy.display.Generators.Manipulators;

import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;

public interface StyleManipulator {
    void changeFill(int fromOffset, int toOffset, SvgRoot root);
}
