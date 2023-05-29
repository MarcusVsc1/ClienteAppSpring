package com.ex.clienter.util;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.ex.clienter.config.BlobConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

@Component
public class BlobUtils {

    private final Gson gson = new Gson();


    @Autowired
    private BlobConfig blobConfig;

    public void salvarMensagemSucessoBlob (Object obj) {

        try {

            LocalDateTime dataAtual = LocalDateTime.now();

            String classe = obj.getClass().getName();

            String content = gson.toJson(obj);
            ByteArrayInputStream dataStream = new ByteArrayInputStream(content.getBytes());

            String blobFile = dataAtual + " - " + classe;

            BlobContainerClient containerClient = blobConfig.blobContainerClientConfig();
            BlobClient blobClient = containerClient.getBlobClient(blobFile);
            blobClient.upload(dataStream);

        } catch (Exception e) {
            System.out.println("Erro ao salvar blob: " + gson.toJson(obj));
        }

    }
}
