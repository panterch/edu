/**
 * 
 */
package ch.fhzh.file;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.fhzh.file.Person.SEX;

/**
 * @author bseelige
 *
 */
public class PersonSerializerTest {
    
    IPersonSerializer instance = new CommaSeperatedPersonSerializer();

    @Test
    public void testSimple() {
        
        Person p = new Person();
        p.setName("Seeliger");
        p.setPrename("Beat");
        p.setSex(SEX.MALE);
        
        String res = instance.serialize(p);
        assertNotNull(res);
        assertEquals("Seeliger,Beat,MALE",res);
        
        
    }

}
