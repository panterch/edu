package com.tddinaction.ejb3.messagedriven;

import javax.ejb.Local;

@Local
public interface SearchService {

    /**
     * Performs a keyword search, returning the IDs of matching assets.
     */
    public String[] search(String[] keywords);
}
