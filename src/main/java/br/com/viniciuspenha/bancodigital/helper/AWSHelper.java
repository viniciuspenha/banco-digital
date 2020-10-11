package br.com.viniciuspenha.bancodigital.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AWSHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AWSHelper.class);

    public String sendImageToS3(byte[] imagem, String fileName) {
        try {
            // Simulando processo de envio de imagem para S3
            LOGGER.info("AWSHelper.sendImageToS3 - enviando imagem para S3...");
            // Retorna url do arquivo no S3
            LOGGER.info("AWSHelper.sendImageToS3 - imagem criada com o nome {}", fileName);
            return "www.exemplo.com.br/" + fileName;
        } catch (Exception e) {
            LOGGER.error("AWSHelper.sendImageToS3 - Falha ao enviar imagem para S3 {}", e.getMessage());
            throw e;
        }
    }
}