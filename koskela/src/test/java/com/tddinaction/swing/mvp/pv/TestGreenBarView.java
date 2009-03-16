package com.tddinaction.swing.mvp.pv;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import com.tddinaction.swing.mvp.common.RunButtonListener;

public class TestGreenBarView {

    private boolean listenerReceivedClick;

    private GreenBarPassiveView view;

    @Before
    public void setUp() {
        view = new GreenBarViewImpl();
    }

    @Test
    public void viewShouldDisplayTheBarInTheGivenColor()
            throws Exception {
        view.setBarColor(Color.GREEN);
        // How to verify that the bar really is green?
    }

    @Test
    public void viewShouldDelegateGesturesToListener()
            throws Exception {
        view.addRunButtonListener(new RunButtonListener() {
            public void onRunButtonClick() {
                listenerReceivedClick = true;
            }
        });
        // How to simulate a click on the "Run" button?
        // How to know whether the button is there at all?
        // assertTrue(listenerReceivedClick);
    }
}
