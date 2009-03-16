package com.tddinaction.swing.plotmap.view;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestPixels {

    @Test
    public void testAround() throws Exception {
        List<Point> pixels = Pixels.around(new Point(5, 5));
        assertEquals(Arrays.asList(new Point(4, 4), new Point(4, 5),
                new Point(4, 6), new Point(5, 4), new Point(5, 6),
                new Point(6, 4), new Point(6, 5), new Point(6, 6)),
                pixels);
    }
}
