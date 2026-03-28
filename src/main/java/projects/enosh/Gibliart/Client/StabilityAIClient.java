package projects.enosh.Gibliart.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projects.enosh.Gibliart.dto.TextToImageRequest;


@FeignClient(
        name = "stabilityAiClient",
        url = "${stability.api.base-url}",
        configuration = projects.enosh.Gibliart.Config.FeignConfig.class
)
public interface StabilityAIClient {

    @PostMapping(
            value = "/v1/generation/{engine_id}/text-to-image",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Accept=image/png"}
    )
    byte[] generateImageFromText(
            @RequestHeader("Authorization") String AuthorizationHeader,
            @PathVariable("engine_id") String engine_id,
            @RequestBody TextToImageRequest requestBody
            );

    @PostMapping(
            value = "/v1/generation/{engine_id}/image-to-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            headers = {"Accept=image/png"}
    )
    byte[] generateImageToImage(
            @RequestHeader("Authorization") String AuthorizationHeader,
            @PathVariable("engine_id") String engine_id,
            @RequestPart("init_image") MultipartFile initImage,
            @RequestPart("text_prompts[0][text]") String textPrompt
    );
}
