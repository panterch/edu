package com.tddinaction.patterns.fixture;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tddinaction.data.person.Person;

public class ParameterizedCreationMethodExample {

    private Person alice, billy, clark;

    @Before
    public void setUp() throws Exception {
        alice = createPerson("Alice", "Adams");
        billy = createPerson("Billy", "Burke");
        clark = createPerson("Clark", "Cable");
        alice.isInLoveWith(billy);
    }

    private Person createPerson(String firstName, String lastName) {
        Person person = new Person();
        person.setFirstname(firstName);
        person.setLastname(lastName);
        person.setId(UniqueNumber.next());
        person.setSsn(String.valueOf(person.getId()));
        return person;
    }

    @Test
    public void aliceShouldAcceptWhenProposedToByBilly()
            throws Exception {
        billy.proposeTo(alice);
        assertTrue(alice.isEngagedWith(billy));
    }

    @Test
    public void aliceShouldNotAcceptWhenProposedToByClark()
            throws Exception {
        clark.proposeTo(alice);
        assertFalse(alice.isEngagedWith(clark));
    }
}
