package co.verisoft.fw.extensions.jupiter;

import lombok.Synchronized;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.platform.engine.TestDescriptor;

import java.lang.reflect.Field;

final public class ExtensionUtilities {
    /**
     * Retrieves the test method arguments from the given ExtensionContext using reflection.
     * The method is annotated with @Synchronized to ensure thread safety.
     *
     * @param extensionContext The ExtensionContext containing information about the test method.
     * @return An array of objects representing the test method arguments, or {@code null} if the arguments cannot be retrieved.
     * @throws NullPointerException If the provided extensionContext is {@code null}.
     */
    @Synchronized
    public static Object[] getTestMethodArgumentsFromExtensionContext(ExtensionContext extensionContext) {
        try {
            Field testDescriptor = extensionContext.getClass().getSuperclass().getDeclaredField("testDescriptor");
            testDescriptor.setAccessible(true);
            TestDescriptor testDescriptorValue = (TestDescriptor) testDescriptor.get(extensionContext); // Get the value of the private field

            Field invocationContext = testDescriptorValue.getClass().getDeclaredField("invocationContext");
            invocationContext.setAccessible(true);
            TestTemplateInvocationContext invocationContextValue = (TestTemplateInvocationContext) invocationContext.get(testDescriptorValue);

            Field arguments = invocationContextValue.getClass().getDeclaredField("arguments");
            arguments.setAccessible(true);
            Object[] argumentsValue = (Object[]) arguments.get(invocationContextValue);
            return argumentsValue;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Test has no arguments");
            return null;
        }
    }
}
