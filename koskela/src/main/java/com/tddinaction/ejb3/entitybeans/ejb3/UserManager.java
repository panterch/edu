package com.tddinaction.ejb3.entitybeans.ejb3;

import javax.ejb.Local;

@Local
public interface UserManager {

    User create(String username, String password);

    User findByUsername(String username);

    void remove(User user);

    void update(User user);

}
