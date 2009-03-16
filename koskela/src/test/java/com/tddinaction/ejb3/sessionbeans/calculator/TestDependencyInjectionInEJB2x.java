package com.tddinaction.ejb3.sessionbeans.calculator;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.junit.Test;

public class TestDependencyInjectionInEJB2x {

    public static class SomeEJB2xSessionBean implements SessionBean {

        public Object dependency;

        protected Object lookupDependencyFromJNDI() {
            throw new RuntimeException(
                    "This method would do a 'new InitialContext()' etc.");
        }

        public void ejbCreate() {
            dependency = lookupDependencyFromJNDI();
        }

        public void ejbActivate() throws EJBException,
                RemoteException {
        }

        public void ejbPassivate() throws EJBException,
                RemoteException {
        }

        public void ejbRemove() throws EJBException, RemoteException {
        }

        public void setSessionContext(SessionContext arg0)
                throws EJBException, RemoteException {
        }
    }

    @Test
    public void dependencyInjectionShouldHappenInEjbCreate()
            throws Exception {
        final Object fakeDependency = new Object();
        SomeEJB2xSessionBean bean = new SomeEJB2xSessionBean() {
            @Override
            protected Object lookupDependencyFromJNDI() {
                return fakeDependency;
            }
        };
        assertNull(bean.dependency);
        bean.ejbCreate();
        assertSame(fakeDependency, bean.dependency);
    }
}
