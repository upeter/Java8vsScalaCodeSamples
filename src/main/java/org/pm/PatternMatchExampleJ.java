package org.pm;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatchExampleJ {

	private static final Pattern MAIL_ADR = Pattern.compile("([^@]+)@(.+)");

	public static Object match(Object obj) {
		if (obj != null) {
			if (obj instanceof String && obj.equals("Scala")) {
				return "(1) A great programming language";
			}
			if (obj instanceof String) {
				Matcher matcher = MAIL_ADR.matcher((String) obj);
				if (matcher.find()) {
					return "(2) spaming " + matcher.group(1) + " @ "
							+ matcher.group(2);
				}
			}
			if (obj instanceof List) {
				List aList = (List) obj;
				if (aList.size() > 3 && aList.get(1) instanceof Integer
						&& ((Integer) aList.get(1)) == 3) {
					return "(3) What is going on here?";
				}
				if (!aList.isEmpty()) {
					return "(4) first " + aList.get(0) + " rest "
							+ aList.subList(1, aList.size());
				}
			}
			if (obj instanceof String && obj.equals("Matching is cool")) {
				return "(5) Is it?";
			}
			if (obj instanceof RequestJ) {
				RequestJ req = (RequestJ) obj;
				if ("GET".equals(req.getMethod())) {
					return "(6) Going to " + req.getPath();
				}
			}
			if (obj instanceof Integer) {
				String result;
				switch ((Integer) obj) {
				case 5:
					result = "(7) It's mambo number ...";
					break;
				default:
					result = "(8) All except 5";
				}
				return result;
			}
		}
		return "(9) Something else";
	}

	class RequestJ {
		private String method;
		private String path;

		public String getMethod() {
			return method;
		}
		public String getPath() {
			return path;
		}
	}
	public static void main(String[] args) {
	}

}
