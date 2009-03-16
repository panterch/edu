package com.tddinaction.swing.plotmap.view;

import java.awt.Component;
import java.awt.Point;
import java.util.Arrays;

import junit.extensions.abbot.ComponentTestFixture;
import abbot.finder.matchers.NameMatcher;
import abbot.tester.ComponentTester;

import com.tddinaction.swing.plotmap.model.PlotMapModel;

public class TestPlotMapViewImpl extends ComponentTestFixture
        implements PlotAdditionListener {

    public TestPlotMapViewImpl(String name) {
        super(name);
    }

    private Point addedPoint;

    private ComponentTester tester;

    private PlotMapViewImpl view;

    private PlotMapCanvasStub canvas;

    public void plotWasAdded(Point plot) {
        addedPoint = plot;
    }

    public void setUp() throws Exception {
        super.setUp();
        tester = new ComponentTester();
        addedPoint = null;
        canvas = new PlotMapCanvasStub();
        view = new PlotMapViewImpl() {
            @Override
            protected PlotMapCanvas createCanvas() {
                return canvas;
            }
        };
        view.registerAdditionListener(this);
        showFrame(view);
    }

    private void typeIntoTextField(String name, String value)
            throws Exception {
        tester.actionKeyString(namedComponent(name), value);
    }

    private Component namedComponent(String name) throws Exception {
        return getFinder().find(new NameMatcher(name));
    }

    public void testAdditionEventGetsTriggered() throws Exception {
        Point point = new Point(3, 5);
        typeIntoTextField("x_coord_textfield", "" + point.x);
        typeIntoTextField("y_coord_textfield", "" + point.y);
        tester.actionClick(namedComponent("add_button"));
        assertEquals(point, addedPoint);
    }

    public void testViewPassesModelToSeparateCanvasObjectForDrawing()
            throws Exception {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(2, 1);
        Point p3 = new Point(5, 4);
        PlotMapModel model = new PlotMapModel();
        model.add(p1);
        model.add(p2);
        model.add(p3);
        view.drawPlotMap(model);
        assertEquals(Arrays.asList(p1, p2, p3), canvas.plottedPoints);
    }

    public void testViewCreatesItsCanvasJustOnce() throws Exception {
        final MutableInt canvasesCreated = new MutableInt();
        view = new PlotMapViewImpl() {
            @Override
            protected PlotMapCanvas createCanvas() {
                canvasesCreated.increment();
                return new PlotMapCanvasStub();
            }
        };
        PlotMapModel model = new PlotMapModel();
        model.add(new Point(1, 1));
        model.add(new Point(2, 3));
        view.drawPlotMap(model);
        view.drawPlotMap(model);
        assertEquals(1, canvasesCreated.intValue());
    }

    public void testViewClearsOutCanvasBeforeDrawingAPlotMapModel()
            throws Exception {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        PlotMapModel model = new PlotMapModel();
        model.add(p1);
        model.add(p2);
        view.drawPlotMap(model);
        view.drawPlotMap(model);
        assertEquals(Arrays.asList(p1, p2), canvas.plottedPoints);
    }

    public void testViewCreatesTheCorrectCanvasImplementation()
            throws Exception {
        PlotMapCanvas canvas = new PlotMapViewImpl().createCanvas();
        assertEquals(PlotMapCanvasImpl.class, canvas.getClass());
    }
}
