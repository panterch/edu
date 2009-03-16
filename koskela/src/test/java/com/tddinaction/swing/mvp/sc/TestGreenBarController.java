package com.tddinaction.swing.mvp.sc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tddinaction.swing.mvp.common.Colors;
import com.tddinaction.swing.mvp.common.GreenBarModel;
import com.tddinaction.swing.mvp.common.GreenBarModelStub;

public class TestGreenBarController {

    private GreenBarObservingViewStub view;

    private GreenBarModelStub model;

    @Before
    public void setUp() {
        view = new GreenBarObservingViewStub();
        model = new GreenBarModelStub();
        new GreenBarController(view, model);
    }

    @Test
    public void shouldPopulateViewWithDefaultValues()
            throws Exception {
        assertEquals(Colors.getDefault(), view.getBarColor());
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

    @Test
    public void userGestureShouldTriggerChangeInModel()
            throws Exception {
        final List<String> testsWereRun = new ArrayList<String>();
        final List<GreenBarModel> updatesReceivedByView = new ArrayList<GreenBarModel>();
        model = new GreenBarModelStub() {
            @Override
            public void runTests() {
                testsWereRun.add("tests were run...");
            }
        };
        view = new GreenBarObservingViewStub() {
            @Override
            public void update(GreenBarModel model) {
                updatesReceivedByView.add(model);
            }
        };
        new GreenBarController(view, model);
        view.registeredRunButtonListener.onRunButtonClick();
        assertTrue(testsWereRun.contains("tests were run..."));
        assertTrue(updatesReceivedByView.contains(model));
    }
}
