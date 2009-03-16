package com.tddinaction.ejb3.entitybeans.ejb2x;

import java.rmi.RemoteException;

import javax.annotation.Resource;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerService;

public abstract class EJB2UserBean implements EntityBean, TimedObject {

    private static final long EXPIRATION_PERIOD = 5000L;

    private EntityContext ctx;

    public abstract void setId(Integer id);

    public abstract Integer getId();

    public abstract void setUsername(String username);

    public abstract String getUsername();

    public abstract void setPassword(String password);

    public abstract String getPassword();

    public abstract void setPasswordExpired(boolean expired);

    public abstract boolean getPasswordExpired();

    public void setEntityContext(EntityContext ctx)
            throws EJBException, RemoteException {
        this.ctx = ctx;
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
        getTimerService().createTimer(EXPIRATION_PERIOD,
                "password expired");
    }

    @Resource
    javax.ejb.TimerService timerService;

    private TimerService getTimerService() {
        // EJB3-style:
        // return timerService;
        
        // EJB2-style:
         return ctx.getTimerService();
    }

    public void ejbTimeout(Timer timer) {
        setPasswordExpired(true);
    }

    public void unsetEntityContext() throws EJBException,
            RemoteException {
    }

    public void ejbRemove() throws RemoveException, EJBException,
            RemoteException {
    }

    public void ejbActivate() throws EJBException, RemoteException {
    }

    public void ejbPassivate() throws EJBException, RemoteException {
    }

    public void ejbLoad() throws EJBException, RemoteException {
    }

    public void ejbStore() throws EJBException, RemoteException {
    }

}
