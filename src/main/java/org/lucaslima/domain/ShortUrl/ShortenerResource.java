package org.lucaslima.domain.ShortUrl;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/shorten")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShortenerResource{

    public ShortenerResource(ShortenerService service) {
        this.service = service;
    }

    @Inject
    ShortenerService service;

    @POST
    public Uni<ResponseShortUrlDto> shortenUrl(RequestShortUrlDto request) {
        return service.createShortUrl(request.getOriginalUrl())
                .map(shortUrl -> new ResponseShortUrlDto(shortUrl.originalUrl, shortUrl.shortCode, shortUrl.shortUrl));
    }

    @GET
    @Path("/all")
    public Uni<Response> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/info/{shortCode}")
    public Uni<Response> info(@PathParam("shortCode") String shortCode) {
        return service.findByShortCode(shortCode)
                .onItem().ifNotNull().transform(url -> Response.ok(url).build())
                .onItem().ifNull().continueWith(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/{shortCode}")
    public Uni<Response> redirect(@PathParam("shortCode") String shortCode) {
        return service.resolveShortCode(shortCode);
    }

}