package co.verisoft.fw.utils;

import lombok.Synchronized;

/**
 * Utilities class
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 */
public final class Utils {

    private Utils() { /* Do not instantiate */ }


    /**
     * get the stack trace from throwable exception
     *
     * @param e throwable object to extract stack trace from
     * @return string equal to throwable exception from the stack trace
     */
    @Synchronized
    public static String getStackTrace(Throwable e) {
        StringBuilder sb = new StringBuilder(500);
        StackTraceElement[] st = e.getStackTrace();
        sb.append(e.getClass().getName()).append(": ").append(e.getMessage()).append("\n");
        for (StackTraceElement stackTraceElement : st) sb.append("\t at " + stackTraceElement.toString() + "\n");
        return sb.toString();
    }
}
