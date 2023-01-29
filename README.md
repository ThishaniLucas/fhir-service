### To test the module
* Clone the repo
* Do `./gradlew clean build -PpublishToLocalCentral=true`
* Create a new b7a project with following service
```ballerina
import thishani/fhir;
import wso2healthcare/healthcare.clients.fhirr4;
import ballerina/http;

configurable string base = ?;
configurable string tokenUrl = ?;
configurable string clientId = ?;
configurable string clientSecret = ?;
configurable string[] scopes = ?;
configurable string domain = ?;


fhirr4:FHIRConnectorConfig cernerConfig = {
    baseURL: base,
    mimeType: fhirr4:FHIR_JSON
};

http:OAuth2ClientCredentialsGrantConfig cernerOauth = {
    tokenUrl: tokenUrl,
    clientId: clientId,
    clientSecret: clientSecret,
    scopes: scopes
};

http:ClientConfiguration cernerClientConfig = {
    auth: cernerOauth
};

final fhirr4:FHIRConnector fhirConnectorObj = check new (cernerConfig, cernerClientConfig);


service / on new fhir:Listener(9090) {
    resource function post Patient (http:RequestContext ctx) returns fhirr4:FHIRResponse|error? {
        fhir:FHIRContext fctx = check ctx.get(fhir:FHIRCtx).ensureType();
        fhirr4:FHIRResponse fhirResponse = check fhirConnectorObj->create(fctx.getFHIRPayload());
        return fhirResponse;
    }
}
```
