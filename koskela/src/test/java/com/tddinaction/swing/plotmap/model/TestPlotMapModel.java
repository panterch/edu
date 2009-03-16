package com.tddinaction.swing.plotmap.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestPlotMapModel {

    private PlotMapModel model;

    private Point p1, p2, p3;

    @Before
    public void setUp() {
        model = new PlotMapModel();
        p1 = new Point(1, 1);
        p2 = new Point(2, 2);
        p3 = new Point(3, 3);
    }

    @Test
    public void emptyModelReturnsEmptyList() throws Exception {
        assertTrue(model.points().isEmpty());
    }

    @Test
    public void nonEmptyModelReturnsAllPointsInTheSameOrderTheyWereAdded()
            throws Exception {
        addThreePoints();
        assertEquals(Arrays.asList(p1, p2, p3), model.points());
    }

    @Test
    public void plotsCanBeRemoved() throws Exception {
        addThreePoints();
        model.remove(p2);
        assertEquals(Arrays.asList(p1, p3), model.points());
    }

    @Test
    public void plotRemovalDoesNotRequireSameObjectInstance()
            throws Exception {
        addThreePoints();
        model.remove(new Point(p2.x, p2.y));
        assertEquals(Arrays.asList(p1, p3), model.points());
    }

    private void addThreePoints() {
        model.add(p1);
        model.add(p2);
        model.add(p3);
    }
}
