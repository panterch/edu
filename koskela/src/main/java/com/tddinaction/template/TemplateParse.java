package com.tddinaction.template;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParse {

    public List<Segment> parseSegments(String template) {
        List<Segment> segments = new ArrayList<Segment>();
        List<String> strings = parse(template);
        for (String s : strings) {
            if (isVariable(s)) {
                String name = s.substring(2, s.length() - 1);
                segments.add(new Variable(name));
            } else {
                segments.add(new PlainText(s));
            }
        }
        return segments;
    }

    private boolean isVariable(String segment) {
        return segment.startsWith("${") && segment.endsWith("}");
    }

    public List<String> parse(String template) {
        List<String> segments = new ArrayList<String>();
        int index = collectSegments(template, segments, 0);
        addTail(segments, template, index);
        return segments;
    }

    private int collectSegments(String src, List<String> segs,
            int index) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            addPrecedingPlainText(segs, src, matcher, index);
            addVariable(segs, src, matcher);
            index = matcher.end();
        }
        return index;
    }

    private void addTail(List<String> segs, String src, int index) {
        if (index < src.length())
            segs.add(src.substring(index));
    }

    private void addVariable(List<String> segs, String src, Matcher m) {
        segs.add(src.substring(m.start(), m.end()));
    }

    private void addPrecedingPlainText(List<String> segs, String src,
            Matcher matcher, int index) {
        if (index != matcher.start()) {
            segs.add(src.substring(index, matcher.start()));
        }
    }

}
