package util;

import java.text.DecimalFormat;
import java.util.Random;

public class Utility {
    private static final Random random = new Random();

    public static String format(long value) {

        return new DecimalFormat("#,###,###").format(value);
    }

    public static int[] generatedSorted(int size, int maxValue) {
        int[] arr = new Random().ints(size,1,maxValue+1)
                .distinct().limit(size).sorted().toArray();

        return arr;

    }

    public static int random(int min, int max) {   //min y max incluyentes
        return min + random.nextInt((max - min) + 1);
    }
}
