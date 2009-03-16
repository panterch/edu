package com.tddinaction.swing.plotmap.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PlotMapCanvasImpl extends Canvas implements
        PlotMapCanvas {

    private List<Point> plots = new ArrayList<Point>();

    public PlotMapCanvasImpl() {
        setSize(200, 100);
        setBackground(Color.WHITE);
    }

    public void clear() {
    }

    public void plot(Point point) {
        plots.add(point);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        Point previous = null;
        for (Point p : plots) {
            if (previous == null) {
                previous = p;
            }
            g.drawLine(previous.x, previous.y, p.x, p.y);
            previous = p;
        }
    }

    public void addRemoveListener(final PointEventListener listener) {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                if (plots.contains(point)) {
                    listener.onPointEvent(point);
                }
            }
        });
    }
}
