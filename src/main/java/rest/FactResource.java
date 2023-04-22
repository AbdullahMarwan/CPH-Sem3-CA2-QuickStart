package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.APIManager;
import utils.EMF_Creator;
import facades.FacadeExample;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("fact")
public class FactResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String generateRandomFact() throws Exception {
        APIManager api = new APIManager();
        String returntext = String.valueOf(api.factAPI());
        return GSON.toJson(returntext);
    }

}
