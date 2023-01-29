package org.wso2.healthcare.fhir.compiler;

import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.projects.plugins.CodeAnalysisContext;
import io.ballerina.projects.plugins.CodeAnalyzer;

/**
 * The {@code CodeAnalyzer} for Ballerina fhir services.
 */
public class FHIRServiceAnalyzer extends CodeAnalyzer {
    @Override
    public void init(CodeAnalysisContext codeAnalysisContext) {
        codeAnalysisContext.addSyntaxNodeAnalysisTask(new FHIRServiceValidator(), SyntaxKind.SERVICE_DECLARATION);
    }
}
