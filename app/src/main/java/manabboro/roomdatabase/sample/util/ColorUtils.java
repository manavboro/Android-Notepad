package manabboro.roomdatabase.sample.util;

import android.graphics.Color;

import java.util.Random;

public class ColorUtils {
    /**
     * Code taken from https://www.codegrepper.com/code-examples/csharp/generate+random+light+color+android
     */
    final static Random mRandom = new Random(System.currentTimeMillis());
    public static int generateRandomColor() {

        // This is the base color which will be mixed with the generated one
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        return Color.rgb(red, green, blue);
    }

}
