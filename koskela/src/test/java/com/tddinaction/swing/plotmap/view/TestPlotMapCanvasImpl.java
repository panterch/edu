package com.tddinaction.swing.plotmap.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.List;

import junit.extensions.abbot.ComponentTestFixture;

import org.junit.Test;

public class TestPlotMapCanvasImpl extends ComponentTestFixture {

    public TestPlotMapCanvasImpl(String name) {
        super(name);
    }

    private PlotMapCanvasImpl canvas;

    private List<Point> removedPoints;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        removedPoints = new ArrayList<Point>();
        canvas = new PlotMapCanvasImpl();
        canvas.addRemoveListener(new PointEventListener() {
            public void onPointEvent(Point point) {
                removedPoints.add(point);
            }
        });
    }

    @Test
    public void testDimensionsShouldBeAutomaticallySet()
            throws Exception {
        showFrame(canvas);
        assertEquals(new Dimension(200, 100), canvas.getSize());
        assertEquals(canvas.getSize(), canvas.getBounds().getSize());
    }

    @Test
    public void testBackgroundColorIsWhite() throws Exception {
        assertEquals(Color.WHITE, canvas.getBackground());
    }

    @Test
    public void testPlotIsDrawnOnScreen() throws Exception {
        canvas.plot(new Point(2, 2));
        Raster raster = Bitmap.of(canvas);
        Pixel.in(raster).at(2, 2).shouldBe(Color.BLACK);
        Pixel.in(raster).around(2, 2).shouldBe(Color.WHITE);
    }

    @Test
    public void testPlotsShouldBeConnectedWithLine() throws Exception {
        canvas.plot(new Point(3, 10));
        canvas.plot(new Point(6, 7));
        Raster raster = Bitmap.of(canvas);
        Pixel.in(raster).at(3, 10).shouldBe(Color.BLACK);
        Pixel.in(raster).at(4, 9).shouldBe(Color.BLACK);
        Pixel.in(raster).at(5, 8).shouldBe(Color.BLACK);
        Pixel.in(raster).at(6, 7).shouldBe(Color.BLACK);
    }

    @Test
    public void testPlotsShouldBeConnected() throws Exception {
        canvas.plot(new Point(2, 9));
        canvas.plot(new Point(5, 6));
        Raster raster = Bitmap.of(canvas);
        Pixels.in(raster).between(2, 9).and(5, 6).shouldBe(
                Color.BLACK);
        Pixel.in(raster).at(2, 9).shouldBe(Color.BLACK);
        Pixel.in(raster).at(3, 8).shouldBe(Color.BLACK);
        Pixel.in(raster).at(4, 7).shouldBe(Color.BLACK);
        Pixel.in(raster).at(5, 6).shouldBe(Color.BLACK);
    }

    public void testClickOnPlottedPointShouldTriggerRemoveEvent()
            throws Exception {
        Point point = new Point(5, 20);
        canvas.plot(point);
        simulateMouseClickAt(point.x, point.y);
        assertTrue(removedPoints.contains(point));
    }

    public void testClickOnNonPlottedPointShouldBeIgnored()
            throws Exception {
        canvas.plot(new Point(100, 50));
        simulateMouseClickAt(20, 30);
        assertTrue(removedPoints.isEmpty());
    }

    private void simulateMouseClickAt(int x, int y) {
        canvas.dispatchEvent(new MouseEvent(canvas,
                MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                MouseEvent.BUTTON1_DOWN_MASK, x, y, 1, false));
    }
}
