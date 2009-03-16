package com.tddinaction.concurrency.asynchexecution;

import org.junit.Assert;
import org.junit.Test;

public class TestAsynchronousServiceByPolling {

    @Test
    public void testAfterLongWait() throws Exception {
        LongLastingCalculation calc = new LongLastingCalculation();
        calc.start();
        Thread.sleep(2000);
        Assert.assertEquals(42, calc.getResult());
    }

    @Test
    public void testByRetryingTheAssertOften() throws Exception {
        final LongLastingCalculation calc = new LongLastingCalculation();
        calc.start();
        new RetriedAssert(2000, 100) {
            @Override
            public void run() {
                Assert.assertEquals(42, calc.getResult());
            }
        }.start();
    }
}
