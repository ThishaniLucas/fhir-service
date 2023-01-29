import ballerina/http;

public isolated class Listener {

    private final http:Listener ls;
    private final http:ListenerConfiguration lconfig = {
        interceptors: [new ReqInterceptor()]
    };

    public isolated function init(int port, *http:ListenerConfiguration config) returns error? {
        self.ls = check new(port, self.lconfig);
    }

    public isolated function 'start() {
        error? 'startResult = self.ls.'start();
        if 'startResult is error {
            // ignore for the moment
        }
    }

    public isolated function gracefulStop() {
        error? gracefulStopResult = self.ls.gracefulStop();
        if gracefulStopResult is error {
            // ignore for the moment
        }
    }

    public isolated function immediateStop() {
        error? immediateStopResult = self.ls.immediateStop();
        if immediateStopResult is error {
            // ignore for the moment
        }
    }

    public isolated function attach(Service fhirService, string[]|string? name = ()) {
        error? attachResult = self.ls.attach(fhirService, name);
        if attachResult is error {
            // ignore for the moment
        }
    }

    public isolated function detach(Service fhirService) {
        error? detachResult = self.ls.detach(fhirService);
        if detachResult is error {
            // ignore for the moment
        }
    }
}
