package com.michalso.svaggy.display.Utils;

import java.util.Random;

public class ManipUtils {

    public static int getRandom(int from, int to) {
        Random rand = new Random();

        return rand.nextInt(to - from) + from;
    }

    public static int getRandom(Random rand, int from, int to) {
        return rand.nextInt(to - from) + from;
    }

}
