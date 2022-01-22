package co.verisoft.fw;

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;


/**
 * Wraps a logger and add aditional functionality like logging caller traces
 * etc.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 1.9.6
 */
@ToString
public class ExtendedLog implements Logger {

    private Logger logger;

    /**
     * Creates a new Logger instance with a designated configuration name
     *
     * @param LoggerName The name of the log
     */
    public ExtendedLog(String LoggerName) {
        logger = LoggerFactory.getLogger(LoggerName);

    }

    /**
     * create logger with class name
     *
     * @param clazz
     */
    public ExtendedLog(final Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }


    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        logger.trace(msg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void trace(String format, Object arg) {
        logger.trace(format, arg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(format, arguments);
        logger.trace(getCallerTrace());
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(msg, t);
        logger.trace(getCallerTrace());
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg) {
        logger.trace(marker, msg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, format, arg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        logger.trace(marker, format, argArray);
        logger.trace(getCallerTrace());
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker, msg, t);
        logger.trace(getCallerTrace());
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void debug(String format, Object arg) {
        logger.debug(format, arg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(format, arguments);
        logger.trace(getCallerTrace());
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
        logger.trace(getCallerTrace());
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg) {
        logger.debug(marker, msg);
        logger.trace(getCallerTrace());

    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, format, arg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        logger.debug(marker, format, arguments);
        logger.trace(getCallerTrace());
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker, msg, t);
        logger.trace(getCallerTrace());
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();

    }

    @Override
    public void info(String msg) {
        logger.info(msg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void info(String format, Object arg) {
        logger.info(format, arg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(format, arguments);
        logger.trace(getCallerTrace());
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.info(msg, t);
        logger.trace(getCallerTrace());
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);

    }

    @Override
    public void info(Marker marker, String msg) {
        logger.info(marker, msg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        logger.info(marker, format, arg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        logger.info(marker, format, arguments);
        logger.trace(getCallerTrace());
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        logger.info(marker, msg, t);
        logger.trace(getCallerTrace());
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void warn(String format, Object arg) {
        logger.warn(format, arg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(format, arguments);
        logger.trace(getCallerTrace());
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
        logger.trace(getCallerTrace());
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String msg) {
        logger.warn(marker, msg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, format, arg);
        logger.trace(getCallerTrace());

    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        logger.warn(marker, format, arguments);
        logger.trace(getCallerTrace());
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker, msg, t);
        logger.trace(getCallerTrace());
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void error(String format, Object arg) {
        logger.error(format, arg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.error(format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(format, arguments);
        logger.trace(getCallerTrace());
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(msg, t);
        logger.trace(getCallerTrace());
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg) {
        logger.error(marker, msg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        logger.error(marker, format, arg);
        logger.trace(getCallerTrace());
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, format, arg1, arg2);
        logger.trace(getCallerTrace());
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        logger.error(marker, format, arguments);
        logger.trace(getCallerTrace());
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        logger.error(marker, msg, t);
        logger.trace(getCallerTrace());
    }

    /**
     * Prints out the class and method chain who created this log entry. The class
     * and method are filtered to show only co.verisoft classes and methods. Method
     * skips the current method and class (the log method and DelegatingLogger
     * class)
     *
     * @return a trace with class name and method name from top to the method with
     * the log entry
     */
    private String getCallerTrace() {

        String callTrace = "Caller Trace: ";

        for (int i = 3; i < Thread.currentThread().getStackTrace().length; i++) {
            String s = Thread.currentThread().getStackTrace()[i].getClassName();
            if (s.contains("co.verisoft")) {
                s = s.substring(s.lastIndexOf('.') + 1);
                String m = Thread.currentThread().getStackTrace()[i].getMethodName() + " line: "
                        + Thread.currentThread().getStackTrace()[i].getLineNumber();
                callTrace += "CLASS: " + s + " METHOD: " + m + " ";
            }
        }

        return callTrace;
    }
}
