package com.tddinaction.ejb3.entitybeans.ejb2x;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface UserHome extends EJBLocalHome {

    public User findByPrimaryKey(Integer id) throws FinderException;

    public User create(String username, String password) throws CreateException;
}
