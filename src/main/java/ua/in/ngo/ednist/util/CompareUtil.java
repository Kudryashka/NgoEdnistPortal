package ua.in.ngo.ednist.util;

public final class CompareUtil {

	public static boolean equalOrBothNull(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null) {
			return true;
		}
		return obj1 != null && obj1.equals(obj2);
	}
	
	public static boolean equalOrBothNull(Object[] objs1, Object[] objs2) {
		if (objs1 == null || objs2 == null) {
			throw new IllegalArgumentException("Arrays should not be null.");
		}
		if (objs1.length != objs2.length) {
			throw new IllegalArgumentException("Arrays has different length");
		}
		for (int i = 0; i < objs1.length; ++i) {
			if (!equalOrBothNull(objs1[i], objs2[i])) {
				return false;
			}
		}
		return true;
	}
}
