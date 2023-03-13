package co.verisoft.fw.visual_regression_tracker;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * Parameter Resolver class that implements the ParameterResolver interface
 * to provide an instance of the required parameter ExtensionContext
 * that necessary for classes that extend with VisualRegressionTrackerExtension and need to use ExtensionContext object
 *
 * @author Efrat Cohen
 * @since March 2023
 */
public class VRTParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == ExtensionContext.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext;
    }
}
