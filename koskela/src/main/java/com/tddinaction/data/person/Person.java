package com.tddinaction.data.person;

public class Person {

    private Long id;

    private String firstName;

    private String lastName;

    private String ssn;

    private Person inLoveWith;

    private Person engagedWith;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String name) {
        this.firstName = name;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String name) {
        this.lastName = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        Person other = (Person) obj;
        return firstName.equals(other.firstName)
                && lastName.equals(other.lastName);
    }

    public String toString() {
        return "{" + firstName + " " + lastName + "}";
    }

    public void proposeTo(Person beloved) {
        if (beloved.acceptProposalFrom(this)) {
            this.engagedWith = beloved;
            beloved.engagedWith = this;
        }
    }

    private boolean acceptProposalFrom(Person person) {
        return person.equals(inLoveWith);
    }

    public void isInLoveWith(Person beloved) {
        inLoveWith = beloved;
    }

    public boolean isEngagedWith(Person person) {
        return person.equals(engagedWith);
    }
}
