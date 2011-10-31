package com.github.joschi.jadconfig;

/**
 * Utility functions for {@link String}
 *
 * @author jschalanda
 */
public final class Strings {

    private Strings() {
    }

    /**
     * Returns a copy of the string, with leading and trailing whitespace omitted.
     *
     * @param s A {@link String}
     * @return A copy of this string with leading and trailing white space removed, this string if it has no leading
     *         or trailing white space, or {@literal null} if the input string was {@literal null}.
     * @see String#trim()
     */
    public static String trim(String s) {

        return null == s ? null : s.trim();
    }
}
