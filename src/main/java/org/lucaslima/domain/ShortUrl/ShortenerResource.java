package org.lucaslima.domain.ShortUrl;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;

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
    public Uni<ResponseShortUrlDto> encode(RequestShortUrlDto request) {
        return service.createShortUrl(request.getOriginalUrl())
                .map(shortUrl -> new ResponseShortUrlDto(shortUrl.originalUrl, shortUrl.shortCode, shortUrl.shortUrl, shortUrl.createdAt));
    }

    @GET
    @Path("/all")
    public Uni<Response> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/info/{shortCode}")
    public Uni<Response> infoUrl(@PathParam("shortCode") String shortCode) {
        return service.findByShortCode(shortCode)
                .onItem().ifNotNull().transform(url -> Response.ok(url).build())
                .onItem().ifNull().continueWith(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{shortCode}")
    public Uni<Response> delete(@PathParam("shortCode") String shortCode) {
        return service.delete(shortCode);
    }

    @GET
    @Path("/{shortCode}")
    public Uni<Response> redirect(@PathParam("shortCode") String shortCode) {
        return service.resolveShortCode(shortCode)
                .onItem().ifNotNull().transform(url -> {
                    try {
                        return Response.seeOther(new URI(url.getOriginalUrl())).build();
                    } catch (URISyntaxException e) {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity("Invalid URL format: " + e.getMessage()).build();
                    }
                })
                .onItem().ifNull().continueWith(() ->
                        Response.status(Response.Status.NOT_FOUND)
                                .entity("Short URL not found").build());
    }


}