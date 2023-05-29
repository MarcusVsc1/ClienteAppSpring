package com.ex.clienter.config;


import com.azure.identity.AzureAuthorityHosts;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-azure.yml")
public class BlobConfig {

    @Value("${blob.storage.endpoint}")
    private String storageEndpoint;

    @Value("${blob.container.directory}")
    private String directory;

    @Bean
    public BlobContainerClient blobContainerClientConfig() {
        return new BlobServiceClientBuilder()
                .endpoint(storageEndpoint)
                .credential(new DefaultAzureCredentialBuilder()
                        .authorityHost(AzureAuthorityHosts.AZURE_GOVERNMENT)
                        .build())
                .buildClient()
                .getBlobContainerClient(directory);
    }

}
