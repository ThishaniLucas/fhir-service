package org.wso2.healthcare.fhir.compiler;

import io.ballerina.compiler.api.symbols.ParameterSymbol;
import io.ballerina.compiler.api.symbols.ResourceMethodSymbol;
import io.ballerina.compiler.api.symbols.Symbol;
import io.ballerina.compiler.syntax.tree.FunctionDefinitionNode;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;
import io.ballerina.tools.diagnostics.DiagnosticFactory;
import io.ballerina.tools.diagnostics.DiagnosticInfo;
import io.ballerina.tools.diagnostics.DiagnosticSeverity;
import io.ballerina.tools.diagnostics.Location;

import java.util.List;
import java.util.Optional;

/**
 * Validates a ballerina fhir resource.
 */
public class FHIRResourceValidator {

    static void validateResource(SyntaxNodeAnalysisContext ctx, FunctionDefinitionNode member) {

        extractInputParamTypeAndValidate(ctx, member);
    }

    public static void extractInputParamTypeAndValidate(SyntaxNodeAnalysisContext ctx, FunctionDefinitionNode member) {

        Optional<Symbol> resourceMethodSymbolOptional = ctx.semanticModel().symbol(member);
        Location paramLocation = member.location();
        if (resourceMethodSymbolOptional.isEmpty()) {
            return;
        }
        Optional<List<ParameterSymbol>> parametersOptional =
                ((ResourceMethodSymbol) resourceMethodSymbolOptional.get()).typeDescriptor().params();
        if (parametersOptional.get().size() != 1) {
            updateDiagnostic(ctx, paramLocation);
            return;
        }
        if (!parametersOptional.get().get(0).typeDescriptor().getName().get().equals("RequestContext")) {
            updateDiagnostic(ctx, paramLocation, parametersOptional.get().get(0).typeDescriptor().getName().get());
            return;
        }
    }

    public static void updateDiagnostic(SyntaxNodeAnalysisContext ctx, Location location, Object... argName) {

        DiagnosticInfo diagnosticInfo = getDiagnosticInfo(argName);
        ctx.reportDiagnostic(DiagnosticFactory.createDiagnostic(diagnosticInfo, location));
    }

    public static void updateDiagnostic(SyntaxNodeAnalysisContext ctx, Location location) {

        DiagnosticInfo diagnosticInfo = getDiagnosticInfo();
        ctx.reportDiagnostic(DiagnosticFactory.createDiagnostic(diagnosticInfo, location));
    }

    public static DiagnosticInfo getDiagnosticInfo(Object... arg) {

        return new DiagnosticInfo("HTTP_105", String.format("invalid resource parameter %s", arg),
                                  DiagnosticSeverity.ERROR);
    }

    public static DiagnosticInfo getDiagnosticInfo() {

        return new DiagnosticInfo("HTTP_105", "invalid number of parameters",
                                  DiagnosticSeverity.ERROR);
    }

}
