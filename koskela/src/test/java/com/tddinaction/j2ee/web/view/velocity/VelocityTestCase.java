package com.tddinaction.j2ee.web.view.velocity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class VelocityTestCase {

    private VelocityContext context;

    private Document document;

    @Before
    public void setUp() throws Exception {
        context = new VelocityContext();
    }

    protected String getWebRoot() {
        return ".";
    }

    protected Document getResponse() {
        return document;
    }

    protected void setAttribute(String name, Object value) {
        context.put(name, value);
    }

    protected void render(String templatePath) throws Exception {
        String template = readFileContent(new File(getWebRoot(),
                templatePath));
        String renderedHtml = renderTemplate(template);
        this.document = parseHtml(renderedHtml);
    }

    private String renderTemplate(String template) throws Exception {
        VelocityEngine engine = new VelocityEngine();
        engine.init();
        StringWriter writer = new StringWriter();
        engine.evaluate(context, writer, "test", template);
        return writer.toString();
    }

    private Document parseHtml(String html) throws Exception {
        return parseXml(html);
    }

    private Document parseXml(String xml) throws Exception {
        DocumentBuilder b = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        return b.parse(new ByteArrayInputStream(xml.getBytes()));
    }

    private String readFileContent(File file) throws Exception {
        FileReader reader = new FileReader(file);
        StringWriter writer = new StringWriter();
        char[] buffer = new char[8096];
        int r = -1;
        while ((r = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, r);
        }
        reader.close();
        return String.valueOf(writer);
    }

    protected void assertFormFieldPresent(String name)
            throws Exception {
        assertNodeExists(xpathForField(name));
    }

    protected void assertFormSubmitButtonPresent(String name)
            throws Exception {
        assertNodeExists("//form//input[@type='submit' and @name='"
                + name + "']");
    }

    protected void assertFormFieldValue(String name,
            String expectedValue) throws Exception {
        String xpath = xpathForField(name);
        assertFormFieldPresent(name);
        String actual = getString(xpath + "/@value");
        assertEquals(expectedValue, actual);
    }

    private String xpathForField(String name) {
        return "//form//input[@name='" + name + "']";
    }

    private void assertNodeExists(String xpath) throws Exception {
        assertNotNull("Node doesn't exist: " + xpath, getNode(xpath));
    }

    private Node getNode(String xpath) throws Exception {
        return (Node) evaluate(xpath, XPathConstants.NODE);
    }

    private String getString(String xpath) throws Exception {
        return (String) evaluate(xpath, XPathConstants.STRING);
    }

    private Object evaluate(String xpath, QName type)
            throws Exception {
        XPath engine = XPathFactory.newInstance().newXPath();
        return engine.evaluate(xpath, getResponse(), type);
    }

    protected void assertTextPresent(String text) throws Exception {
        StringBuilder buffer = new StringBuilder(10000);
        collectTextContent(document, buffer);
        assertTrue("Text not found on page: " + text, buffer
                .toString().contains(text));
    }

    private void collectTextContent(Node node, StringBuilder buffer) {
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                buffer.append(child.getNodeValue());
            } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                collectTextContent(child, buffer);
            }
        }
    }
}
