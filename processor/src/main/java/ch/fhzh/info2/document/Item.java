package ch.fhzh.info2.document;

import java.io.Serializable;
import java.util.Currency;

/**
 * Item held on document
 * @author bseelige
 *
 */
public interface Item extends Serializable {

    /**
     * @return Returns the cent.
     */
    public abstract long getCent();

    /**
     * @param cent The cent to set.
     */
    public abstract void setCent(long cent);

    /**
     * @return Returns the cur.
     */
    public abstract Currency getCur();

    /**
     * @param cur The cur to set.
     */
    public abstract void setCur(Currency cur);

    /**
     * @return Returns the desc.
     */
    public abstract String getDesc();

    /**
     * @param desc The desc to set.
     */
    public abstract void setDesc(String desc);

    /**
     * @return Returns the id.
     */
    public abstract String getId();

    /**
     * @param id The id to set.
     */
    public abstract void setId(String id);

    /**
     * @return Returns the orderNo.
     */
    public abstract String getOrderNo();

    /**
     * @param orderNo The orderNo to set.
     */
    public abstract void setOrderNo(String orderNo);

}