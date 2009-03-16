package com.tddinaction.swing.mvp.pv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.tddinaction.swing.mvp.common.Colors;
import com.tddinaction.swing.mvp.common.GreenBarModelStub;

public class TestGreenBarPresenter {

    private GreenBarViewStub view;

    private GreenBarModelStub model;

    @Before
    public void setUp() {
        view = new GreenBarViewStub();
        model = new GreenBarModelStub();
        new GreenBarPresenter(view, model);
    }

    @Test
    public void shouldPopulateViewWithDefaultValues()
            throws Exception {
        assertEquals(Colors.getDefault(), view.barColor);
    }

    @Test
    public void shouldRegisterForUserGestures() throws Exception {
        assertNotNull(view.registeredRunButtonListener);
    }

    @Test
    public void userGestureTriggersChangeInModelAndViewShouldReflect()
            throws Exception {
        model.testsWillPassOnNextTestRun = true;
        view.registeredRunButtonListener.onRunButtonClick();
        assertEquals(Colors.testsPassed(), view.barColor);

        model.testsWillPassOnNextTestRun = false;
        view.registeredRunButtonListener.onRunButtonClick();
        assertEquals(Colors.testsFailed(), view.barColor);
    }
}
