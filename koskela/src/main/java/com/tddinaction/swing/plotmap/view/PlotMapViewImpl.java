package com.tddinaction.swing.plotmap.view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tddinaction.swing.plotmap.model.PlotMapModel;

public class PlotMapViewImpl extends JPanel implements PlotMapView {

    private PlotAdditionListener additionListener;

    private JTextField xCoordField, yCoordField;

    private JButton addButton;

    private PlotMapCanvas canvas;

    public PlotMapViewImpl() {
        createWidgets();
        add(xCoordField);
        add(yCoordField);
        add(addButton);
    }

    private void createWidgets() {
        xCoordField = createTextField("x_coord_textfield");
        yCoordField = createTextField("y_coord_textfield");
        addButton = new JButton();
        addButton.setName("add_button");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int x = valueAsInt(xCoordField);
                int y = valueAsInt(yCoordField);
                additionListener.plotWasAdded(new Point(x, y));
            }

        });
        canvas = createCanvas();
    }

    protected PlotMapCanvas createCanvas() {
        return new PlotMapCanvasImpl();
    }

    private int valueAsInt(JTextField field) {
        return Integer.parseInt(field.getText());
    }

    private JTextField createTextField(String name) {
        JTextField field = new JTextField();
        field.setName(name);
        return field;
    }

    public void registerAdditionListener(PlotAdditionListener listener) {
        this.additionListener = listener;
    }

    public void registerRemovalListener(PlotRemovalListener listener) {
        // TODO: we haven't yet started implementing the remove operation!
    }

    public void drawPlotMap(PlotMapModel model) {
        canvas.clear();
        for (Point point : model.points()) {
            canvas.plot(point);
        }
    }
}
