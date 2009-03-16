package com.tddinaction.fit.fixtures;

import fit.ColumnFixture;

/**
 * This custom fixture provides a way to populate data into the system under
 * test in a row-by-row basis, treating each row as something we need to add
 * somewhere.
 * 
 * @author lkoskela
 */
public class HandmadeSetUpFixture extends ColumnFixture {

    public String name;

    public String email;

    @Override
    public void reset() throws Exception {
        name = null;
        email = null;
    }

    @Override
    public void execute() throws Exception {
        // adding user 'name' with email address 'email' to the system
    }

}
