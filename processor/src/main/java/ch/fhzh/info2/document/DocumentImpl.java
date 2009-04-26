/**
 * 
 */
package ch.fhzh.info2.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;

/**
 * DocumentImpl
 * 
 * @author bseelige
 *
 */
public class DocumentImpl implements Document, Serializable {
    

    /**
     * content of document ready to print
     */
    private String printRepresentation;
    
    /** document type */
    private Type type;
    
    /** document unique id */
    private String id;
    
    /** timestamp of last modification */
    private Calendar modTime;
    
    /** customer reference */
    private String reference;
    
    /** collections of items on document */
    private List<Item> items;
    
    /** currency of the document */
    private Currency cur;
    
    /** total amount of document costs */
    private long totalCent;

    /**
     * Creates and initializes the document
     */
    public DocumentImpl() {
        super();
        this.items = new ArrayList<Item>();
    }
    
    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#addItem(ch.fhzh.info2.document.Item)
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#getCur()
     */
    public Currency getCur() {
        return cur;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#setCur(java.util.Currency)
     */
    public void setCur(Currency cur) {
        this.cur = cur;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#getId()
     */
    public String getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#setId(java.lang.String)
     */
    public void setId(String id) {
        this.id = id;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#getItems()
     */
    public List<Item> getItems() {
        return items;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#setItems(java.util.List)
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#getReference()
     */
    public String getReference() {
        return reference;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#setReference(java.lang.String)
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#getTotalCent()
     */
    public long getTotalCent() {
        return totalCent;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#setTotalCent(long)
     */
    public void setTotalCent(long totalCent) {
        this.totalCent = totalCent;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#getType()
     */
    public Type getType() {
        return type;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Document#setType(ch.fhzh.info2.document.DocumentImpl.Type)
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return Returns the printRepresentation.
     */
    public String getPrintRepresentation() {
        return printRepresentation;
    }

    /**
     * @param printRepresentation The printRepresentation to set.
     */
    public void setPrintRepresentation(String printRepresentation) {
        this.printRepresentation = printRepresentation;
    }

	/**
	 * @return the modTime
	 */
	public Calendar getModTime() {
		return modTime;
	}

	/**
	 * @param modTime the modTime to set
	 */
	public void setModTime(Calendar modTime) {
		this.modTime = modTime;
	}
    
    

}
