package com.tddinaction.swing.plotmap.view;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

import com.tddinaction.swing.plotmap.view.Pixel.PixelAssertion;

public class Pixels {

    public static class PixelRangeAssertion {

        private final Raster raster;

        private int startX;

        private int startY;

        public PixelRangeAssertion(Raster raster) {
            this.raster = raster;
        }

        PixelRangeAssertion between(int x, int y) {
            this.startX = x;
            this.startY = y;
            return this;
        }

        PixelAssertion and(final int targetX, final int targetY) {
            return new PixelAssertion() {

                class PixelFinder {
                    private final Set<Point> visited;

                    private final Point target;

                    private final Color color;

                    public PixelFinder(Set<Point> visited,
                            Point target, Color color) {
                        this.visited = visited;
                        this.target = target;
                        this.color = color;
                    }

                    public boolean isConnectedTo(
                            List<Point> siblingPoints) {
                        for (Point p : siblingPoints) {
                            if (p.x == targetX && p.y == targetY) {
                                return true;
                            }
                            if (visited.contains(p)) {
                                continue;
                            }
                            visited.add(p);
                            if (Pixel.in(raster).at(p.x, p.y).is(
                                    color)) {
                                PixelFinder recursiveFinder = new PixelFinder(
                                        visited, target, color);
                                return recursiveFinder
                                        .isConnectedTo(Pixels
                                                .around(p));
                            }
                        }
                        return false;
                    }
                }

                public void shouldBe(Color color) {
                    Assert.assertTrue("No connecting pixels of "
                            + color + " between (" + startX + ","
                            + startY + ") and (" + targetX + ","
                            + targetY + ")", is(color));
                }

                public boolean is(Color color) {
                    Set<Point> visited = new HashSet<Point>();
                    visited.add(new Point(startX, startY));
                    List<Point> siblingPoints = Pixels
                            .around(new Point(startX, startY));
                    return new PixelFinder(visited, new Point(
                            targetX, targetY), color)
                            .isConnectedTo(siblingPoints);
                }
            };
        };
    }

    public static PixelRangeAssertion in(final Raster raster) {
        return new PixelRangeAssertion(raster);
    }

    public static List<Point> around(Point point) {
        List<Point> points = new ArrayList<Point>();
        for (int x2 = point.x - 1; x2 <= point.x + 1; x2++) {
            for (int y2 = point.y - 1; y2 <= point.y + 1; y2++) {
                if (x2 != point.x || y2 != point.y) {
                    points.add(new Point(x2, y2));
                }
            }
        }
        return points;
    }

}
