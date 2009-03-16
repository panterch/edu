package com.tddinaction.template;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestTemplateParse {

    private List<String> parse(String template) {
        return new TemplateParse().parse(template);
    }

    private void assertSegments(List<? extends Object> actual,
            Object... expected) {
        assertEquals("Number of segments doesn't match.",
                expected.length, actual.size());
        assertEquals(Arrays.asList(expected), actual);
    }

    @Test
    public void emptyTemplateRendersAsEmptyString() throws Exception {
        assertTrue(parse("").isEmpty());
    }

    @Test
    public void templateWithOnlyPlainText() throws Exception {
        List<String> segments = parse("plain text only");
        assertSegments(segments, "plain text only");
    }

    @Test
    public void parsingMultipleVariables() throws Exception {
        List<String> segments = parse("${a}:${b}:${c}");
        assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");
    }

    @Test
    public void parsingTemplateIntoSegmentObjects() throws Exception {
        TemplateParse p = new TemplateParse();
        List<Segment> segments = p.parseSegments("a ${b} c ${d}");
        assertSegments(segments, new PlainText("a "), new Variable(
                "b"), new PlainText(" c "), new Variable("d"));
    }

}
