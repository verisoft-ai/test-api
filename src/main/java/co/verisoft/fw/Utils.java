package co.verisoft.fw;

import lombok.Synchronized;

public class Utils {

    /**
     * get the stack trace from throwable exception
     *
     * @param e throwable object to extract stack trace from
     * @return string equal to throwable exception from the stack trace
     */
    @Synchronized
    public static String getStackTrace(Throwable e) {
        StringBuffer sb = new StringBuffer(500);
        StackTraceElement[] st = e.getStackTrace();
        sb.append(e.getClass().getName() + ": " + e.getMessage() + "\n");
        for (int i = 0; i < st.length; i++) {
            sb.append("\t at " + st[i].toString() + "\n");
        }
        return sb.toString();
    }
}
