package org.c_pm;

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
		if (obj instanceof String) {
			String value = (String) obj;
			if(value.length() > 10) {
				return "(3) A rather long String " + value;
			}
		}
		if (obj instanceof List) {
			@SuppressWarnings("rawtypes")
			List aList = (List) obj;
			if (aList.size() > 3 && aList.get(1) instanceof Integer
					&& ((Integer) aList.get(1)) == 3) {
				return "(4) What is going on here?";
			}
			if (!aList.isEmpty()) {
				return "(5) first " + aList.get(0) + " rest "
						+ aList.subList(1, aList.size());
			}
		}
		if (obj instanceof Integer) {
			switch ((Integer) obj) {
			case 5:
				return "(6) It's mambo number ...";
			default:
				return "(7) All except 5";
			}
		}
	}
	return "(8) Something else";
}



}
