package org.t7b.services;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

public class S3ClientService {
    @Produces
    @Singleton
    public S3Client s3Client(
            @ConfigProperty(name = "quarkus.s3.endpoint-override") String endpoint,
            @ConfigProperty(name = "quarkus.s3.aws.credentials.static-provider.access-key-id") String accessKey,
            @ConfigProperty(name = "quarkus.s3.aws.credentials.static-provider.secret-access-key") String secretKey,
            @ConfigProperty(name = "quarkus.s3.aws.region", defaultValue = "auto") String region) {
        
        S3Configuration s3Config = S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build();
        return S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .serviceConfiguration(s3Config)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.of(region))
                .build();
    }
}