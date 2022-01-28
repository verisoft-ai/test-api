package co.verisoft.fw.utils;

import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.*;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@ToString
@Plugin(name="TestAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
class TestAppender extends AbstractAppender {


    @Getter
    private List<String> logMessages;

    @Getter
    private List<LogEvent> logEvents;

    private final Layout<? extends Serializable> layout;


    public TestAppender(final String name, final Layout<? extends Serializable> layout, final Filter filter,
                        final boolean ignoreExceptions){

        super(name, filter, layout, ignoreExceptions);
        this.layout = layout;
        this.logEvents = new ArrayList<>();
        this.logMessages = new ArrayList<>();

    }



    @Override
    public void append(LogEvent event) {

        String message = "";
        try{
            message = new String(layout.toByteArray(event), "UTF-8");

        } catch(Exception e){

            // If the log failed, we want the test to fail. In production log appender we would handle this exception
            error("Unable to log message in appender [" + getName() + "]", event, e);        }

        logEvents.add(event);
        logMessages.add(message);
    }


    @PluginFactory
    public static TestAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter) {
        if (name == null) {
            LOGGER.error("No name provided for MyCustomAppenderImpl");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new TestAppender(name, layout, filter, true);
    }



    @Override
    public void start() {
        super.start();
    }

}
