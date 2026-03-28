package projects.enosh.Gibliart.dto;

import lombok.Data;

@Data
public class TextGenerationRequestDTO {
    private String prompt;
    private String style;
    private String size;
}
