package org.github.denakh.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class NumberUtility {

    public static final Random RANDOM = new Random();

    public static Integer getIntInRange(int min, int max) {
        if (min == max) {
            return min;
        }
        return RANDOM.nextInt(max - min) + min;
    }
    public static Integer getIntInRange(int max) {
        return getIntInRange(0, max);
    }

}
