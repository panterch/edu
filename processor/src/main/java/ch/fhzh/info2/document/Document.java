package ch.fhzh.info2.document;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;

/**
 * Interface of document business object
 * @author seb
 *
 */
public interface Document extends Serializable {
    
    /**
     * Enumeration of possible document
     * types
     * @author bseelige
     *
     */
    public static enum Type {
        INVOICE,
        ORDER,
        BASKET,
        /** used for unit tests */
        TEST
    }
    
    /**
     * Shortcut accessor to item collection's
     * add method
     * @param item the new item to add
     */
    public abstract void addItem(Item item);

    /**
     * @return Returns the cur.
     */
    public abstract Currency getCur();

    /**
     * @param cur The cur to set.
     */
    public abstract void setCur(Currency cur);

    /**
     * @return Returns the id.
     */
    public abstract String getId();

    /**
     * @param id The id to set.
     */
    public abstract void setId(String id);

    /**
     * @return Returns the items.
     */
    public abstract List<Item> getItems();

    /**
     * @param items The items to set.
     */
    public abstract void setItems(List<Item> items);

    /**
     * @return Returns the reference.
     */
    public abstract String getReference();

    /**
     * @param reference The reference to set.
     */
    public abstract void setReference(String reference);

    /**
     * @return Returns the totalCent.
     */
    public abstract long getTotalCent();

    /**
     * @param totalCent The totalCent to set.
     */
    public abstract void setTotalCent(long totalCent);

    /**
     * @return Returns the type.
     */
    public abstract Type getType();

    /**
     * @param type The type to set.
     */
    public abstract void setType(Type type);
    
    /**
     * @return Returns the printRepresentation.
     */
    public String getPrintRepresentation();
    
    /**
     * @param printRepresentation The printRepresentation to set.
     */
    public void setPrintRepresentation(String printRepresentation);

	/**
	 * @return the modTime
	 */
	public Calendar getModTime();

	/**
	 * @param modTime the modTime to set
	 */
	public void setModTime(Calendar modTime);
    
}