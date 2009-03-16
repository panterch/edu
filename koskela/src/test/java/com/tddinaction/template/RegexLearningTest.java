package com.tddinaction.template;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegexLearningTest {

    @Test
    public void testHowGroupCountWorks() throws Exception {
        String haystack = "The needle shop sells needles";
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        assertEquals(1, matcher.groupCount());
    }

    public void testFindStartAndEnd() throws Exception {
        String haystack = "The needle shop sells needles";
        String regex = "needle";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match.", 4, matcher.start());
        assertEquals("Wrong end index of 1st match.", 10, matcher.end());

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 2nd match.", 22, matcher.start());
        assertEquals("Wrong end index of 2nd match.", 28, matcher.end());

        assertFalse("Should not have any more matches", matcher.find());
    }

    // -----------------------------------

    @Test
    public void testMatchesMatchesExactMatchOrNot() throws Exception {
        assertTrue(Pattern.compile("exact").matcher("exact")
                .matches());
        assertFalse(Pattern.compile("exact").matcher("exactamundo")
                .matches());
        assertFalse(Pattern.compile("exact").matcher("exactamundo")
                .matches());
        assertFalse(Pattern.compile("exact").matcher("inexactamundo")
                .matches());
    }

    @Test
    public void testHowTheGroupCountMethodWorks() throws Exception {
        String haystack = "The needle shop sells needles";
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        assertEquals(
                "There was only one set of parenthesis in the regex",
                1, matcher.groupCount());
    }

    @Test
    public void testFindStartEndGroup() throws Exception {
        String haystack = "The needle shop sells needles";
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        assertTrue(matcher.find());
        assertEquals("1st 'needle' starts at index 4", 4, matcher
                .start());
        assertEquals("1st 'needle' ends at index 10", 10, matcher
                .end());
        assertEquals("needle", matcher.group());
        assertTrue(matcher.find());
        assertEquals("2nd 'needle' starts at index 22", 22, matcher
                .start());
        assertEquals("2nd 'needle' ends at index 28", 28, matcher
                .end());
        assertEquals("needle", matcher.group());
        assertFalse("Should be no more matches", matcher.find());
    }
}
