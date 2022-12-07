package org.acme.microprofile.health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {

    @ConfigProperty(name = "database.up", defaultValue = "false")
    boolean databaseUp;

    @Override
    public HealthCheckResponse call() {

        var b = HealthCheckResponse.named("db conn health check");

        try {
            databaseConnCheck();
            b.up();
        } catch (IllegalStateException e) {
            b.down()
                    .withData("server", "up")
                    .withData("schema", "migration failed");
        }

        return b.build();
    }


    void databaseConnCheck() {

        if (databaseUp) return;

        throw new IllegalStateException("database not available");
    }

}
