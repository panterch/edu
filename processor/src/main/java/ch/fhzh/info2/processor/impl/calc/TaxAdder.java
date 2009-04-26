/**
 * 
 */
package ch.fhzh.info2.processor.impl.calc;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Adds defined tax percentage on document total
 * amount
 * 
 * @author bseelige
 *
 */
public class TaxAdder implements DocumentProcessor {

    /** the tax rate to apply, may be injected */
    private double taxRate = 0.0d;
    
    public Document processDocument(Document doc) throws DocumentProcessorException {
        double total = doc.getTotalCent();
        total += taxRate * doc.getTotalCent();
        doc.setTotalCent(Math.round(total));
        return doc;
    }

    /**
     * @return Returns the taxRate.
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate The taxRate to set.
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
    
    

}
