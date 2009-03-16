package com.tddinaction.ejb3.entitybeans.ejb2x;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.classextension.EasyMock.createMock;

import javax.ejb.EntityContext;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.junit.Assert;
import org.junit.Test;

public class PasswordExpiryTimerTest {

    @Test
    public void timerIsCreatedUponCreatingNewUserAccount()
            throws Exception {
        TimerService timers = createMock(TimerService.class);
        EntityContext context = createMock(EntityContext.class);
        Timer timer = createMock(Timer.class);
        expect(context.getTimerService()).andReturn(timers);
        expect(timers.createTimer(5000, "password expired"))
                .andReturn(timer);

        replay(context, timers, timer);

        EJB2UserBean entity = new UserBeanStub();
        entity.setEntityContext(context);
        entity.ejbPostCreate("someusername", "somepassword");

        verify(context, timers, timer);
    }

    protected boolean passwordExpired = false;

    @Test
    public void flagIsSetWhenTimerGoesOff() throws Exception {
        EJB2UserBean entity = new UserBeanStub() {
            @Override
            public void setPasswordExpired(boolean expired) {
                passwordExpired = expired;
            }
        };
        Assert.assertFalse(passwordExpired);
        entity.ejbTimeout(null);
        Assert.assertTrue(passwordExpired);
    }

    /**
     * Concrete implementation of the entity bean under test.
     */
    public static class UserBeanStub extends EJB2UserBean {

        @Override
        public void setId(Integer id) {
        }

        @Override
        public Integer getId() {
            return null;
        }

        @Override
        public void setUsername(String username) {
        }

        @Override
        public String getUsername() {
            return null;
        }

        @Override
        public void setPassword(String password) {
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public void setPasswordExpired(boolean expired) {
        }

        @Override
        public boolean getPasswordExpired() {
            return false;
        }
    }
}
