package com.tddinaction.swing.plotmap.view;

import java.awt.Color;
import java.awt.image.Raster;

import static org.junit.Assert.*;

public class Pixel {

    public interface PixelAssertion {
        void shouldBe(Color color);

        boolean is(Color color);
    }

    private final Raster raster;

    public static Pixel in(Raster raster) {
        return new Pixel(raster);
    }

    private Pixel(Raster raster) {
        this.raster = raster;
    }

    public PixelAssertion at(final int x, final int y) {
        return new PixelAssertion() {
            public void shouldBe(Color color) {
                assertPixelColor(x, y, color);
            }

            public boolean is(Color color) {
                return color.equals(getPixelColor(x, y));
            }
        };
    }

    public PixelAssertion around(final int x, final int y) {
        return new PixelAssertion() {
            public void shouldBe(Color color) {
                for (int x2 = x - 1; x2 < x + 1; x2++) {
                    for (int y2 = y - 1; y2 < y + 1; y2++) {
                        if (x2 != x || y2 != y) {
                            assertPixelColor(x2, y2, color);
                        }
                    }
                }
            }

            public boolean is(Color black) {
                for (int x2 = x - 1; x2 < x + 1; x2++) {
                    for (int y2 = y - 1; y2 < y + 1; y2++) {
                        if (x2 != x || y2 != y) {
                            return false;
                        }
                    }
                }
                return true;
            }
        };
    }

    private void assertPixelColor(int x, int y, Color expected) {
        Color actual = getPixelColor(x, y);
        assertEquals("(" + x + "," + y + ").", expected, actual);
    }

    private Color getPixelColor(int x, int y) {
        int[] pixel = raster.getPixel(x, y, new int[3]);
        return new Color(pixel[0], pixel[1], pixel[2]);
    }

    public static void assertPixelColor(Raster raster, int x, int y,
            Color expected) {
        Color actual = getPixelColor(raster, x, y);
        assertEquals("(" + x + "," + y + ").", expected, actual);
    }

    public static Color getPixelColor(Raster raster, int x, int y) {
        int[] pixel = raster.getPixel(x, y, new int[3]);
        return new Color(pixel[0], pixel[1], pixel[2]);
    }
}
