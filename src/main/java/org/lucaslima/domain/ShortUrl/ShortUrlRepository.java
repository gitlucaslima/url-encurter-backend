package org.lucaslima.domain.ShortUrl;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShortUrlRepository implements ReactivePanacheMongoRepository<ShortUrl> {

    public Uni<ShortUrl> findByShortCode(String shortCode) {
        return find("shortCode", shortCode).firstResult();
    }
}
