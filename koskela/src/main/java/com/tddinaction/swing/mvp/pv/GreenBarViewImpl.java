package com.tddinaction.swing.mvp.pv;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.tddinaction.swing.mvp.common.RunButtonListener;

public class GreenBarViewImpl extends JPanel implements
        GreenBarPassiveView {

    private RunButtonListener listener;

    private JPanel bar;

    public GreenBarViewImpl() {
        add(createRunButton());
        add(createBar());
    }

    private Component createRunButton() {
        JButton button = new JButton("Run");
        button.setName("run_button");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listener.onRunButtonClick();
            }
        });
        return button;
    }

    private Component createBar() {
        bar = new JPanel();
        bar.setName("bar");
        bar.add(Box.createRigidArea(new Dimension(200, 20)));
        return bar;
    }

    public void setBarColor(Color color) {
        bar.setBackground(color);
        bar.setForeground(color);
    }

    public void addRunButtonListener(RunButtonListener listener) {
        this.listener = listener;
    }
}
