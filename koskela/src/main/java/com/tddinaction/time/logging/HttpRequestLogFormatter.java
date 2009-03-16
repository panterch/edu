package com.tddinaction.time.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.tddinaction.time.abstraction.SystemTime;

public class HttpRequestLogFormatter {

	public static DateFormat dateFormat = new SimpleDateFormat(
			"dd/MMM/yyyy:HH:mm:ss Z");

	public String format(HttpServletRequest request, int httpStatus,
			int contentLength) {
		StringBuffer line = new StringBuffer();
		line.append(request.getRemoteAddr());
		line.append(" - ");
		line.append(request.getRemoteUser());
		line.append(" [");
		line.append(dateFormat.format(SystemTime.asDate()));
		line.append("] \"").append(request.getMethod());
		line.append(" ").append(request.getRequestURI());
		line.append(" ").append(request.getProtocol());
		line.append("\" ").append(httpStatus);
		line.append(" ").append(contentLength);
		return line.toString();
	}
}
