package org.acme.microprofile.health;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthResource {

    @GET
    public JsonObject reply() {
        return Json.createObjectBuilder()
                .add("status", "OK")
                .build();
    }
}


