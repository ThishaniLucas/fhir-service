import ballerina/http;

service class ReqInterceptor {

    *http:RequestInterceptor;

    resource function 'default [string... path](http:Request res, http:RequestContext ctx) returns http:NextService|error?{
        FHIRContext fctx = check new(check res.getJsonPayload());
        ctx.set("fhirctx", fctx);
        return check ctx.next();
    }
}
