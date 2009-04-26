/**
 * 
 */
package ch.fhzh.info2.document;

import java.io.Serializable;
import java.util.Currency;

/**
 * Item held on a document
 * 
 * @author bseelige
 *
 */
public class ItemImpl implements Item, Serializable {

    /** unique identifier */
    private String id;
    
    /** description */
    private String desc;
    
    /** order number */
    private String orderNo;
    
    /** cent amount unit cost */
    private long cent;
    
    /** currency of the item's cost */
    private Currency cur;

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#getCent()
     */
    public long getCent() {
        return cent;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#setCent(long)
     */
    public void setCent(long cent) {
        this.cent = cent;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#getCur()
     */
    public Currency getCur() {
        return cur;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#setCur(java.util.Currency)
     */
    public void setCur(Currency cur) {
        this.cur = cur;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#getDesc()
     */
    public String getDesc() {
        return desc;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#setDesc(java.lang.String)
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#getId()
     */
    public String getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#setId(java.lang.String)
     */
    public void setId(String id) {
        this.id = id;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#getOrderNo()
     */
    public String getOrderNo() {
        return orderNo;
    }

    /* (non-Javadoc)
     * @see ch.fhzh.info2.document.Item#setOrderNo(java.lang.String)
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (cent ^ (cent >>> 32));
		result = PRIME * result + ((cur == null) ? 0 : cur.hashCode());
		result = PRIME * result + ((desc == null) ? 0 : desc.hashCode());
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		result = PRIME * result + ((orderNo == null) ? 0 : orderNo.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ItemImpl other = (ItemImpl) obj;
		if (cent != other.cent)
			return false;
		if (cur == null) {
			if (other.cur != null)
				return false;
		} else if (!cur.equals(other.cur))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderNo == null) {
			if (other.orderNo != null)
				return false;
		} else if (!orderNo.equals(other.orderNo))
			return false;
		return true;
	}
    
    

}
