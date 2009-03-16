/**
 * 
 */
package ch.panter.edu.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * This class let's you access private fields
 * and field w/o a setter trough reflection.
 * <p>
 * Code copied from Spring Framwork 2.5.
 * </p>
 * 
 * @author bseelige
 *
 */
public class ReflectionTestUtil {


    
    /**
     * <p>
     * Sets the {@link Field field} with the given <code>name</code> on the
     * provided {@link Object target object} to the supplied <code>value</code>.
     * </p>
     * <p>
     * This method traverses the class hierarchy in search of the desired field.
     * In addition, an attempt will be made to make non-<code>public</code>
     * fields <em>accessible</em>, thus allowing one to set
     * <code>protected</code>, <code>private</code>, and
     * <em>package-private</em> fields.
     * </p>
     *
     * @param target The target object on which to set the field.
     * @param name The name of the field to set.
     * @param value The value to set; may be <code>null</code> unless the
     *        field type is a primitive type.
     * @param type The type of the field.
     * @throws IllegalArgumentException if the target object or field type is
     *         <code>null</code>; if the field name is <em>empty</em>; or
     *         if the field could not be found on the target object.
     * @throws Exception Allows all other exceptions to propagate.
     * @see ReflectionUtils#findField(Class, String, Class)
     * @see ReflectionUtils#makeAccessible(Field)
     * @see ReflectionUtils#setField(Field, Object, Object)
     */
    public void setField(final Object target, final String name, final Object value, final Class type)
            throws Exception {

    
    
        final Field field = findField(target.getClass(), name, type);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + name + "] on target [" + target
                    + "] with type [" + type + "].");
        }

        makeAccessible(field);
        setField(field, target, value);
    }
    
    /**
     * Make the given field accessible, explicitly setting it accessible if necessary.
     * The <code>setAccessible(true)</code> method is only called when actually necessary,
     * to avoid unnecessary conflicts with a JVM SecurityManager (if active).
     * @param field the field to make accessible
     * @see java.lang.reflect.Field#setAccessible
     */
    private static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers()) ||
                !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }
    
    /**
     * Set the field represented by the supplied {@link Field field object} on
     * the specified {@link Object target object} to the specified
     * <code>value</code>. In accordance with
     * {@link Field#set(Object, Object)} semantics, the new value is
     * automatically unwrapped if the underlying field has a primitive type.
     * <p>Thrown exceptions are handled via a call to
     * {@link #handleReflectionException(Exception)}.
     * @param field the field to set
     * @param target the target object on which to set the field
     * @param value the value to set; may be <code>null</code>
     */
    private void setField(Field field, Object target, Object value) {
        try {
            field.set(target, value);
        }
        catch (IllegalAccessException ex) {
            handleReflectionException(ex);
            throw new IllegalStateException("Unexpected reflection exception - " + ex.getClass().getName() + ": " +
                    ex.getMessage());
        }
    }
    
    /**
     * Handle the given reflection exception. Should only be called if
     * no checked exception is expected to be thrown by the target method.
     * <p>Throws the underlying RuntimeException or Error in case of an
     * InvocationTargetException with such a root cause. Throws an
     * IllegalStateException with an appropriate message else.
     * @param ex the reflection exception to handle
     */
    private void handleReflectionException(Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            throw new IllegalStateException("Method not found: " + ex.getMessage());
        }
        if (ex instanceof IllegalAccessException) {
            throw new IllegalStateException("Could not access method: " + ex.getMessage());
        }
        throw new RuntimeException(ex);
    }
    
    /**
     * Attempt to find a {@link Field field} on the supplied {@link Class} with
     * the supplied <code>name</code> and {@link Class type}. Searches all
     * superclasses up to {@link Object}.
     * @param clazz the class to introspect
     * @param name the name of the field
     * @param type the type of the field
     * @return the corresponding Field object, or <code>null</code> if not found
     * @throws IllegalArgumentException if the class or field type is
     * <code>null</code> or if the field name is <em>empty</em>
     */
    private Field findField(final Class clazz, final String name, final Class type) {
        Class searchType = clazz;
        while (!Object.class.equals(searchType) && searchType != null) {
            final Field[] fields = searchType.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (name.equals(field.getName()) && type.equals(field.getType())) {
                    return field;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }
    
    

}
