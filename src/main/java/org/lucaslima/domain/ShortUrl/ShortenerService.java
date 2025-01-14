package org.lucaslima.domain.ShortUrl;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.security.SecureRandom;
import java.util.Base64;

@ApplicationScoped
public class ShortenerService {

    @Inject
    ShortUrlRepository repository;

    @Inject
    @ConfigProperty(name = "app.base.url.shorten")
    String urlPrefix; // Variável que armazenará o valor da propriedade

    private static final SecureRandom random = new SecureRandom();

    public Uni<ShortUrl> createShortUrl(String url) {
        String shortCode = generateShortCode();
        return repository.findByShortCode(shortCode)
                .onItem().ifNotNull().transformToUni(existingUrl -> Uni.createFrom().item(new ShortUrl(url, shortCode,generateShortUrl(shortCode))))
                .onItem().ifNull().continueWith(() -> new ShortUrl(url, shortCode, generateShortUrl(shortCode)))
                .chain(shortUrl -> repository.persist(shortUrl))
                .chain(shortUrl -> Uni.createFrom().item(shortUrl));
    }

    public String generateShortCode() {
        byte[] randomBytes = new byte[4];
        random.nextBytes(randomBytes);

        String base64Encoded = Base64.getUrlEncoder().encodeToString(randomBytes);

        return base64Encoded.substring(0, 6).toUpperCase();
    }

    public String generateShortUrl(String shortCode) {
        return urlPrefix + shortCode;
    }

    public Uni<Response> resolveShortCode(String shortCode) {
        return repository.findByShortCode(shortCode)
                .onItem().ifNotNull().transform(url ->
                        Response.status(Response.Status.FOUND)  // Código 302 (redirecionamento)
                                .header("Location", url.getOriginalUrl())
                                .build()
                )
                .onItem().ifNull().continueWith(
                        Response.status(Response.Status.NOT_FOUND)
                                .entity("Short URL not found")
                                .build()
                );
    }

    public Uni<ShortUrl> findByShortCode(String shortCode) {
        return repository.findByShortCode(shortCode);
    }

    public Uni<Response> listAll() {
        return repository.listAll().onItem().transform(shortUrls ->
                Response.status(Response.Status.OK)
                        .entity(shortUrls)
                        .build());
    }


}
