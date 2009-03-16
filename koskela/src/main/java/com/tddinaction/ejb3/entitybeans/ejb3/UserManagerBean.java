package com.tddinaction.ejb3.entitybeans.ejb3;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserManagerBean implements UserManager {

    @PersistenceContext
    private EntityManager em;

    public User create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        em.persist(user);
        return user;
    }

    public User findByUsername(String username) {
        Query query = em.createNamedQuery("findUserByUsername");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    public void remove(User user) {
        em.remove(user);
    }

    public void update(User user) {
        em.merge(user);
    }

}
