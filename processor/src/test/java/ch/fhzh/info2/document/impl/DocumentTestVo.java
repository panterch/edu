/**
 * 
 */
package ch.fhzh.info2.document.impl;

import java.util.Currency;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.document.ItemImpl;

/**
 * Initialized document for unit tests
 * 
 * @author seb
 *
 */
public class DocumentTestVo extends DocumentImpl implements Document {


	public static final long ITEM_CENT = 1200l;
	public static final String ITEM_ID = "123";
	public static final Currency CUR = Currency.getInstance("CHF");
	public static final String ID = "junit doc";
	public static final String REFERENCE = "junit reference";
	public static final Document.Type TYPE = Document.Type.TEST;
	
	/**
	 * Constructs and initializes test
	 * document
	 */
	public DocumentTestVo() {
		this.setId(ID);
		this.setCur(CUR);
		this.setReference(REFERENCE);
		this.setType(TYPE);
        Item item = new ItemImpl();
        item.setId(ITEM_ID);
        item.setCent(ITEM_CENT);
        item.setCur(CUR);
        this.addItem(item);
 
		
	}

}
