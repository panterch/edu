package com.tddinaction.swing.mvp.pv;

import java.awt.Color;
import java.awt.Component;

import junit.extensions.abbot.ComponentTestFixture;
import abbot.finder.matchers.NameMatcher;
import abbot.tester.ComponentTester;

import com.tddinaction.swing.mvp.common.RunButtonListener;

public class TestGreenBarViewWithAbbot extends ComponentTestFixture {

    public TestGreenBarViewWithAbbot(String name) {
        super(name);
    }

    private GreenBarViewImpl view;

    private int buttonClicks;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        view = new GreenBarViewImpl();
        buttonClicks = 0;
        view.addRunButtonListener(new RunButtonListener() {
            public void onRunButtonClick() {
                buttonClicks++;
            }
        });
        showFrame(view);
    }

    public void testViewShouldDisplayTheBarInTheGivenColor()
            throws Exception {
        Color desiredColor = Color.GREEN;
        view.setBarColor(desiredColor);
        Component bar = componentNamed("bar");
        assertEquals(desiredColor, bar.getBackground());
        assertEquals(desiredColor, bar.getForeground());
    }

    public void testViewShouldDelegateGesturesToListener()
            throws Exception {
        Component button = componentNamed("run_button");
        assertEquals(0, buttonClicks);
        new ComponentTester().actionClick(button);
        assertEquals(1, buttonClicks);
    }

    private Component componentNamed(String name) throws Exception {
        return getFinder().find(view, new NameMatcher(name));
    }
}
