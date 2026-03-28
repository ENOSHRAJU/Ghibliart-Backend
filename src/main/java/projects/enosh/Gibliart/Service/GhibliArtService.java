package projects.enosh.Gibliart.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projects.enosh.Gibliart.Client.StabilityAIClient;
import projects.enosh.Gibliart.dto.TextToImageRequest;

@Service
public class GhibliArtService {

    private final StabilityAIClient stabilityAIClient;
    private final String apiKey;

    public GhibliArtService(StabilityAIClient stabilityAIClient, @Value("${stability.api.key}") String apikey) {
        this.stabilityAIClient = stabilityAIClient;
        this.apiKey = apikey;
    }

    public byte[] createGhibliArtFromImage(MultipartFile image, String style, String prompt) {
        String finalPrompt = prompt + ", in beautiful, detailed " + style + "style";
        String engineId = "stable-diffusion-xl-1024-v1-0";

        return stabilityAIClient.generateImageToImage(
                "Bearer "+ apiKey,
                engineId,
                image,
                finalPrompt
        );
    }

    public byte[] createGhibliArtFromText(String prompt, String style, String imageSize) {
        String finalPrompt = prompt + ", in beautiful, detailed style of studio ghibli";
        String engineId = "stable-diffusion-xl-1024-v1-0";

        TextToImageRequest requestPayload = new TextToImageRequest(finalPrompt, style, imageSize);

        return stabilityAIClient.generateImageFromText(
                "Bearer "+ apiKey,
                engineId,
                requestPayload
        );
    }
}
