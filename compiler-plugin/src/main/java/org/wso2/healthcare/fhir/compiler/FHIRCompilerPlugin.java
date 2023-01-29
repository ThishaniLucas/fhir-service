package org.wso2.healthcare.fhir.compiler;

import io.ballerina.projects.plugins.CompilerPlugin;
import io.ballerina.projects.plugins.CompilerPluginContext;

/**
 * The compiler plugin implementation for Ballerina FHIR package.
 */
public class FHIRCompilerPlugin extends CompilerPlugin {

    @Override
    public void init(CompilerPluginContext context) {
        context.addCodeAnalyzer(new FHIRServiceAnalyzer());
    }

}
