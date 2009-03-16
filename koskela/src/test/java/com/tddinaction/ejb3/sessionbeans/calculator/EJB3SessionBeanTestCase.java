package com.tddinaction.ejb3.sessionbeans.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.SessionBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.junit.Test;

/**
 * An abstract base class for testing a session bean. The class contributes test
 * methods for verifying the interface contract between the bean and the EJB
 * container. For example, it checks that the proper class level annotations are
 * in place, that the lifecycle annotations are tacked on the matching ejbXXX
 * methods if the bean implements the EJB 2.X <tt>SessionBean</tt> interface,
 * and that each lifecycle annotation occurs only once.
 * 
 * @author Lasse Koskela
 */
public abstract class EJB3SessionBeanTestCase {

    protected abstract Class<? extends Object> getBeanClass();

    @Test
    public void ejb3CompliantClassAnnotations() {
        Annotation stateless = getBeanClass().getAnnotation(
                Stateless.class);
        Annotation stateful = getBeanClass().getAnnotation(
                Stateful.class);
        assertFalse("Only one of @Stateless or @Stateful is allowed",
                (stateless != null && stateful != null));
    }

    @Test
    public void backwardsCompatibilityWithEjb2Interface()
            throws Exception {
        if (SessionBean.class.isAssignableFrom(getBeanClass())) {
            assertAnnotationIsOnMethod(PreDestroy.class, "ejbRemove");
            assertAnnotationIsOnMethod(PostActivate.class,
                    "ejbActivate");
            assertAnnotationIsOnMethod(PrePassivate.class,
                    "ejbPassivate");
            assertAnnotationIsOnMethod(PostConstruct.class,
                    "ejbCreate");
        }
    }

    @Test
    public void validNumberOfLifecycleAnnotations() throws Exception {
        assertNumberOfAnnotations(1, PreDestroy.class);
        assertNumberOfAnnotations(1, PostActivate.class);
        assertNumberOfAnnotations(1, PrePassivate.class);
        assertNumberOfAnnotations(1, PostConstruct.class);
    }

    private void assertNumberOfAnnotations(int maxOccurrences,
            Class<? extends Annotation> annotation) {
        int count = countMethodsWithAnnotation(annotation);
        assertTrue("@" + annotation.getName()
                + " should occur at most " + maxOccurrences
                + " times (occurred " + count + " times for "
                + getBeanClass().getName() + ")",
                count <= maxOccurrences);
    }

    private Method getMethodWithAnnotation(
            Class<? extends Annotation> annotation) {
        for (Method method : getBeanClass().getMethods()) {
            if (methodHasAnnotation(method, annotation)) {
                return method;
            }
        }
        return null;
    }

    private int countMethodsWithAnnotation(
            Class<? extends Annotation> annotation) {
        int count = 0;
        for (Method method : getBeanClass().getMethods()) {
            if (methodHasAnnotation(method, annotation)) {
                count++;
            }
        }
        return count;
    }

    private void assertAnnotationIsOnMethod(
            Class<? extends Annotation> annotation, String ejb2Method)
            throws Exception {
        if (!beanClassDefinesMethod(ejb2Method)) {
            return;
        }
        Method method = getMethodWithAnnotation(annotation);
        if (method == null) {
            return;
        }
        assertEquals("The @" + annotation.getName()
                + " annotation can only be applied to the "
                + ejb2Method
                + " method if the bean class implements "
                + SessionBean.class.getName(), ejb2Method, method
                .getName());
    }

    private boolean methodHasAnnotation(Method actual,
            Class<? extends Annotation> annotation) {
        return actual.getAnnotation(annotation) != null;
    }

    private boolean beanClassDefinesMethod(String methodName) {
        try {
            getBeanClass().getMethod(methodName, new Class[0]);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
