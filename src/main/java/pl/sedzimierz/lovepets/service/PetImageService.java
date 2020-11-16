package pl.sedzimierz.lovepets.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

@Service
public class PetImageService {

    private final Logger log = LoggerFactory.getLogger(PetImageService.class);

    @Value("${pets.images.directory}")
    private String imagesDirectory;

    public String saveImageAndGetUrl(MultipartFile image, Long petId) {
        try {
            generateDirectory(petId);
            String imageUrl = petId + "/" + image.getOriginalFilename();
            Path path = Paths.get(imagesDirectory + imageUrl);
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            log.info("Saved pet image with url: '{}'", imageUrl);
            return imageUrl;
        } catch (IOException e) {
            log.debug("Error while saving pet image : {}", e.getMessage());
            return "/img/imageNotFound.png";
        }
    }

    public void generateDirectory(Long petId) {
        String path = imagesDirectory + petId;
        File directory = new File(path);

        if (!directory.mkdirs()) {
            Arrays.stream(directory.listFiles())
                    .forEach(File::delete);
        }
    }
}
