package com.tddinaction.ejb3.entitybeans.ejb3;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;

public class UserManagerBeanTest {

    private final String username = "bob";

    @Test
    public void findingUserByUsername() throws Exception {
        EntityManager em = createMock(EntityManager.class);
        Query q = createMock(Query.class);
        User user = createDummyUser(username);

        expect(em.createNamedQuery("findUserByUsername"))
                .andReturn(q);
        expect(q.setParameter("username", username)).andReturn(q);
        expect(q.getSingleResult()).andReturn(user);

        replay(em, q);

        UserManagerBean bean = new UserManagerBean();
        Inject.bean(bean).with(em);
        Assert.assertEquals(user, bean.findByUsername(username));

        verify(em, q);
    }

    private User createDummyUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("secret");
        return user;
    }
}
