package com.tddinaction.ejb3.entitybeans.ejb2x;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.classextension.EasyMock.createMock;

import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.junit.Assert;
import org.junit.Test;

public class PasswordExpiryTimerEJB3Test {

    @Test
    public void timerIsCreatedUponCreatingNewUserAccount()
            throws Exception {
        TimerService timers = createMock(TimerService.class);
        Timer timer = createMock(Timer.class);
        expect(timers.createTimer(5000, "password expired"))
                .andReturn(timer);

        replay(timers, timer);

        EJB3UserBean entity = new EJB3UserBean();
        entity.timerService = timers;
        entity.ejbPostCreate("someusername", "somepassword");

        verify(timers, timer);
    }

    protected boolean passwordExpired = false;

    @Test
    public void flagIsSetWhenTimerGoesOff() throws Exception {
        EJB3UserBean entity = new EJB3UserBean() {
            @Override
            public void setPasswordExpired(boolean expired) {
                passwordExpired = expired;
            }
        };
        Assert.assertFalse(passwordExpired);
        entity.ejbTimeout(null);
        Assert.assertTrue(passwordExpired);
    }
}
