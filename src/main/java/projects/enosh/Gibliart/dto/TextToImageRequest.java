package projects.enosh.Gibliart.dto;

import lombok.Data;

import java.util.List;

@Data
public class TextToImageRequest {

    private List<TextPrompt> text_prompts;
    private double cfg_scale = 7;
    private int samples = 1;
    private int steps = 30;
    private int width;
    private int height;
    private String style_preset;

    //Inner class for the text_prompts
    public static class TextPrompt {
        private String text;
        public TextPrompt(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    //Constructor
    public TextToImageRequest(String text, String style_preset, String imageSize) {
        this.text_prompts = List.of(new TextPrompt(text));
        this.style_preset = style_preset;
        String[] sizeArr = imageSize.split("x");
        this.width = Integer.parseInt(sizeArr[0]);
        this.height = Integer.parseInt(sizeArr[1]);
    }

}
