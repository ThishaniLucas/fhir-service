import ballerina/http;

# The FHIR service type.
public type Service distinct service object {
 *http:Service;
};

public const FHIRCtx = "fhirctx";