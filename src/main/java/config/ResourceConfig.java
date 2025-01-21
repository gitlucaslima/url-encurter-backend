package config;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.ConfigProvider;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceConfig {

    @GET
    @Path("/health")
    public Uni<String> health() {

        String mongodbUri = ConfigProvider.getConfig().getValue("quarkus.mongodb.connection-string", String.class);
        String mongodbDatabase = ConfigProvider.getConfig().getValue("quarkus.mongodb.database", String.class);
        String frontendUrl = ConfigProvider.getConfig().getValue("quarkus.http.cors.origins", String.class);

        //pegar porta em que esta rodando
        String port = ConfigProvider.getConfig().getValue("quarkus.http.port", String.class);

        return Uni.createFrom().item("Quarkus UP and Running! - "+mongodbUri+" - "+mongodbDatabase+"port: "+port+" - "+" Frontend: "+frontendUrl);
    }
}
