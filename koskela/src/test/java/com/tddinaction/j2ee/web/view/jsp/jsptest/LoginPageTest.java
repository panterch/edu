package com.tddinaction.j2ee.web.view.jsp.jsptest;

public class LoginPageTest extends JspTestCase {

    public void testFormFieldsArePresent() throws Exception {
        get("/login.jsp");
        form().shouldHaveField("j_username");
        form().shouldHaveField("j_password");
        form().shouldHaveSubmitButton("login");
    }

    public void testPreviousUsernameIsRetained() throws Exception {
        setRequestAttribute("j_username", "bob");
        get("/login.jsp");
        form().field("j_username").shouldHaveValue("bob");
    }
}
