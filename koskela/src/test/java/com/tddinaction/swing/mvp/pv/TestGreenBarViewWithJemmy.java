package com.tddinaction.swing.mvp.pv;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;

import com.tddinaction.swing.jemmy.NameBasedChooser;
import com.tddinaction.swing.mvp.common.RunButtonListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JFrameOperator;

public class TestGreenBarViewWithJemmy {

    private int buttonClicks;

    private GreenBarViewImpl view;

    private JFrameOperator frameOp;

    @Before
    public void setUp() {
        view = new GreenBarViewImpl();
        buttonClicks = 0;
        view.addRunButtonListener(new RunButtonListener() {
            public void onRunButtonClick() {
                buttonClicks++;
            }
        });
        showFrame(view);
    }

    @After
    public void tearDown() {
        frameOp.dispose();
    }

    private void showFrame(Component component) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
        frameOp = new JFrameOperator(frame);
    }

    @Test
    public void viewShouldDisplayTheBarInTheGivenColor()
            throws Exception {
        Color desiredColor = Color.GREEN;
        view.setBarColor(desiredColor);
        JComponentOperator barOp = new JComponentOperator(frameOp,
                new NameBasedChooser("bar"));
        assertEquals(desiredColor, barOp.getBackground());
        assertEquals(desiredColor, barOp.getForeground());
    }

    @Test
    public void viewShouldDelegateGesturesToListener()
            throws Exception {
        JButtonOperator buttonOp = new JButtonOperator(frameOp,
                new NameBasedChooser("run_button"));
        assertEquals(0, this.buttonClicks);
        buttonOp.doClick();
        assertEquals(1, this.buttonClicks);
    }
}
