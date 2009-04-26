/**
 * 
 */
package ch.fhzh.info2.processor.impl.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Sends the given's document string representation via commons email
 * 
 * @author seb
 * 
 */
public class EmailSendingProcessor implements DocumentProcessor {

	/** host used as mail server */
	private String hostName;

	/** receiver email address */
	private String to;

	/** sender email address */
	private String from;

	public Document processDocument(Document doc)
			throws DocumentProcessorException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName(hostName);
		try {
			email.addTo(to);
			email.setFrom(from);
			email.setSubject("Document");
			email.setMsg(doc.getPrintRepresentation());
			email.send();
		} catch (EmailException e) {
			throw new DocumentProcessorException(e);
		}
		return doc;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

}
