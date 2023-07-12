package nata.project.service.data;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import org.fluttercode.datafactory.impl.DataFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Randomizer {
    public static Integer generateIntegerFromRange(Integer min, Integer max) {
        return (int) (Math.random() * ((max - min) + 1) + min);
    }

    public static Long generateLongFromRange(Long min, Long max) {
        return (long) (Math.random() * ((max - min) + 1L) + min);
    }

    public static BigDecimal generateBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

    public static String generateString(int length) {
        return RandomStringUtils.random(length, true, false);
    }

    public static String generateStringWithDataFactory(int length) {
        return new DataFactory().getRandomText(length);
    }
}
