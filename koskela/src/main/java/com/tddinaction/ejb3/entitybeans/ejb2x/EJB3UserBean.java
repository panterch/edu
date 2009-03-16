package com.tddinaction.ejb3.entitybeans.ejb2x;

import javax.annotation.Resource;
import javax.ejb.CreateException;
import javax.ejb.TimedObject;
import javax.ejb.Timer;

public class EJB3UserBean implements TimedObject {

    private static final long EXPIRATION_PERIOD = 5000L;

    public void setId(Integer id) {
    }

    public Integer getId() {
        return null;
    }

    public void setUsername(String username) {
    }

    public String getUsername() {
        return null;
    }

    public void setPassword(String password) {
    }

    public String getPassword() {
        return null;
    }

    public void setPasswordExpired(boolean expired) {
    }

    public boolean getPasswordExpired() {
        return false;
    }

    public Integer ejbCreate(String user, String pass)
            throws CreateException {
        setId((int) (System.currentTimeMillis() % Integer.MAX_VALUE));
        setUsername(user);
        setPassword(pass);
        setPasswordExpired(false);
        return null;
    }

    public void ejbPostCreate(String username, String password)
            throws CreateException {
        timerService.createTimer(EXPIRATION_PERIOD,
                "password expired");
    }

    @Resource
    javax.ejb.TimerService timerService;

    public void ejbTimeout(Timer timer) {
        setPasswordExpired(true);
    }
}
