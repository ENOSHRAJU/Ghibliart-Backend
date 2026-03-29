package projects.enosh.Gibliart.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projects.enosh.Gibliart.Service.GhibliArtService;
import projects.enosh.Gibliart.dto.TextGenerationRequestDTO;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"https://ghibliart-frontned-1.onrender.com"})
public class GenerationController {

    private final GhibliArtService service;

    public GenerationController(GhibliArtService service) {
        this.service = service;
    }

    @PostMapping(value = "/generate-from-image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateImageFromImage(@RequestParam("image") MultipartFile image,
                                                         @RequestParam("style") String style,
                                                         @RequestParam("prompt") String prompt) {
//        try {
//            byte[] imageBytes = service.createGhibliArtFromImage(image, style, prompt);
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
//        } catch(Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().build();
//        }

        byte[] imageBytes = service.createGhibliArtFromImage(image, style, prompt);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

    @PostMapping(value = "/generate-from-text", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateImageFromText(@RequestBody TextGenerationRequestDTO requestDTO) {
//        try {
//            byte[] imageBytes = service.createGhibliArtFromText(requestDTO.getPrompt(), requestDTO.getStyle(), requestDTO.getSize());
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
//        } catch(Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().build();
//        }

        byte[] imageBytes = service.createGhibliArtFromText(requestDTO.getPrompt(), requestDTO.getStyle(), requestDTO.getSize());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }
}
