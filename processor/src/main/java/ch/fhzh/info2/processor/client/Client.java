/**
 * 
 */
package ch.fhzh.info2.processor.client;

import java.util.Currency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.document.Document.Type;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * @author seb
 *
 */
public class Client {

	/**
	 * @param args
	 * @throws DocumentProcessorException 
	 */
	public static void main(String[] args) throws DocumentProcessorException {
		new Client().run();
	}


	private Currency chf = Currency.getInstance("CHF");
	
	
	public void run() throws DocumentProcessorException {
        /* create beanfactory - would be done central 
         * and more abstract in real life */
		ClassPathResource resource = new ClassPathResource("spring.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        
        /* create a test document holding an item */
        Document doc = (Document)factory.getBean("doc");
        doc.setType(Type.ORDER);
        doc.setCur(chf);
        doc.setReference("client document");
        doc.setId("123");
        Item item = (Item)factory.getBean("item");
        item.setCur(chf);
        item.setCent(1230l);
        item.setId("123");
        item.setDesc("ein item");
        doc.addItem(item);
        
        /* process document */
        DocumentProcessor proc = (DocumentProcessor)factory.getBean("chain");
        doc = proc.processDocument(doc);

	}

}
