package com.tddinaction.patterns.fixture;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.junit.After;
import org.junit.Before;

public abstract class AutomatedTeardownTestCase<T> {

    private List<Object> mockObjects;

    protected T createMock(Class<T> type) {
        T mockObject = EasyMock.createMock(type);
        mockObjects.add(mockObject);
        return mockObject;
    }

    @Before
    public void setUp() throws Exception {
        mockObjects = new ArrayList<Object>();
    }

    @After
    public void tearDown() throws Exception {
        verifyAll();
    }

    protected void replayAll() {
        for (Object mockObject : mockObjects) {
            EasyMock.replay(mockObject);
        }
    }

    private void verifyAll() {
        for (Object mockObject : mockObjects) {
            EasyMock.verify(mockObject);
        }
    }
}
