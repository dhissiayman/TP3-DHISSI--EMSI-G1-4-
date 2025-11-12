package ma.emsi.dhissiayman.tp3.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;
import ma.emsi.dhissiayman.tp3.assistant.GuideTouristique;

import java.io.Serializable;

@Dependent
public class LlmClientForGuideTouristique implements Serializable {

    private static final long serialVersionUID = 1L;

    // Non sérialisables
    private transient ChatModel model;
    private transient GuideTouristique assistant;
    private transient String apiKey;

    private void ensureInit() {
        if (assistant != null) return;

        apiKey = System.getenv("GEMINI_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Clé d'API manquante : GEMINI_KEY");
        }

        model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .logRequestsAndResponses(true) // utile en dev
                .build();

        assistant = AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                // pas de mémoire pour cet assistant, comme demandé
                .build();
    }

    public String infos(String lieu, int nbEndroits) {
        ensureInit();
        return assistant.chat(lieu, nbEndroits);
    }
}
