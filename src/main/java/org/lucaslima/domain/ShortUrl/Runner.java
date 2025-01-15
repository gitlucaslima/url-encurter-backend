package org.lucaslima.domain.ShortUrl;

import io.quarkus.runtime.annotations.CommandLineArguments;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;

@ApplicationScoped
public class Runner {

    // Método chamado ao iniciar a aplicação
    @PostConstruct
    public void printConfigValues() {

        Logger logger = org.slf4j.LoggerFactory.getLogger(Runner.class);

        logger.info("Iniciando a aplicação...");

        // Obtendo as variáveis do application.properties
        String mongodbUri = ConfigProvider.getConfig().getValue("quarkus.mongodb.connection-string", String.class);
        String mongodbDatabase = ConfigProvider.getConfig().getValue("quarkus.mongodb.database", String.class);

        // Printando os valores
        logger.info("Quarkus MongoDB URI: " + mongodbUri);
        logger.info("Quarkus MongoDB Database: " + mongodbDatabase);
    }

    public void run(@CommandLineArguments String[] args) {
        System.out.println("Command-line arguments = " + String.join(", ", args));
    }
}