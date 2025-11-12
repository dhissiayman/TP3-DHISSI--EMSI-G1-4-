package ma.emsi.dhissiayman.tp3.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.dhissiayman.tp3.llm.LlmClientForGuideTouristique;

@Path("/guide")
@Produces(MediaType.APPLICATION_JSON)
public class GuideTouristiqueResource {

    @Inject
    LlmClientForGuideTouristique llm;

    // GET /api/guide/lieu/France        (par défaut 10 endroits ici)
    // GET /api/guide/lieu/Paris?nb=4
    @GET
    @Path("/lieu/{lieu}")
    public Response villeOuPays(@PathParam("lieu") String lieu,
                                @QueryParam("nb") @DefaultValue("10") int nb) {
        String json = llm.infos(lieu, nb);
        return Response.ok(json)
                // CORS + no-cache (utile si test via page HTML locale)
                .header("Access-Control-Allow-Origin", "*")
                .header("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0")
                .header("Pragma", "no-cache")
                .header("Expires", "0")
                .build();
    }
}
