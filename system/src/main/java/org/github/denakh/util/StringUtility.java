package org.github.denakh.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class StringUtility {

    public static String generateStringOfLength(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

}
