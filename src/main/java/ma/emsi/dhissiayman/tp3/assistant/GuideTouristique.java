package ma.emsi.dhissiayman.tp3.assistant;


import dev.langchain4j.service.SystemMessage;

public interface GuideTouristique {
    @SystemMessage("""
        Tu es un guide touristique francophone.
        Réponds STRICTEMENT en JSON (sans Markdown) avec EXACTEMENT ce format :
        {
          "ville_ou_pays": "nom de la ville ou du pays",
          "endroits_a_visiter": ["endroit 1", "endroit 2"],
          "prix_moyen_repas": "<prix> <devise du pays>"
        }
        Ne retourne rien d'autre que ce JSON.
        """)
    String chat(String prompt);
}