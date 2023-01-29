public isolated class FHIRContext {

    private final readonly & json payload;
    
    public isolated function init(json payload) returns error? {
        self.payload = payload.cloneReadOnly();
    }

    public isolated function getFHIRPayload() returns json {
        return self.payload;
    }

    public isolated function setFHIRPayload() {
        
    }
};
