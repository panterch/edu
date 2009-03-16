package com.tddinaction.time.logging;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Test;

import com.tddinaction.time.abstraction.SystemTime;
import com.tddinaction.time.abstraction.TimeSource;

public class TestHttpRequestLogFormatter {

    @After
    public void resetSystemTime() {
        SystemTime.reset();
    }

    @Test
    public void testCommonLogFormat() throws Exception {
        final long time = SystemTime.asMillis();
        SystemTime.setTimeSource(new TimeSource() {
            public long millis() {
                return time;
            }
        });
        DateFormat dateFormat = HttpRequestLogFormatter.dateFormat;
        String timestamp = dateFormat.format(SystemTime.asDate());
        String expected = "1.2.3.4 - bob [" + timestamp
                + "] \"GET /ctx/path/resource HTTP/1.1\" 200 2326";

        HttpServletRequest request = createMock(HttpServletRequest.class);
        expect(request.getRemoteAddr()).andReturn("1.2.3.4");
        expect(request.getRemoteUser()).andReturn("bob");
        expect(request.getMethod()).andReturn("GET");
        expect(request.getRequestURI()).andReturn(
                "/ctx/path/resource");
        expect(request.getProtocol()).andReturn("HTTP/1.1");
        replay(request);

        HttpRequestLogFormatter formatter = new HttpRequestLogFormatter();
        assertEquals(expected, formatter.format(request, 200, 2326));
    }

    @Test
    public void testTimestampFormat() throws Exception {
        String date = "\\d{2}/\\w{3}/\\d{4}";
        String time = "\\d{2}:\\d{2}:\\d{2}";
        String timezone = "(-|\\+)\\d{4}";
        String regex = date + ":" + time + " " + timezone;

        DateFormat dateFormat = HttpRequestLogFormatter.dateFormat;
        String timestamp = dateFormat.format(SystemTime.asDate());
        assertTrue("DateFormat should be \"dd/mon/yyyy:HH:mm:ss Z\"",
                timestamp.matches(regex));
    }
}
