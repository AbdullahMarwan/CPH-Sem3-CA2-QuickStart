package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.QuoteDTO;
import dtos.UserDTO;
import facades.ExternalApiFacade;
import facades.InternalApiFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


import facades.ExternalApiFacade;
@Path("ext")
public class ExternalApiResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ExternalApiFacade EXTERNAL_API_FACADE = ExternalApiFacade.getExternalApiFacade(EMF);
    private static final InternalApiFacade INTERNAL_API_FACADE = InternalApiFacade.getInternalApiFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @GET
    @Path("quote")
    @Produces({MediaType.APPLICATION_JSON})
    public String getQuote() throws Exception {

        try {
            return GSON.toJson(EXTERNAL_API_FACADE.getQuote("https://api.quote.rest/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Quote not found";
    }

    @GET
    @Path("fact")
    @Produces({MediaType.APPLICATION_JSON})
    public String getFact() throws Exception {

        try {
            return GSON.toJson(EXTERNAL_API_FACADE.getFact("https://api.fact.rest/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Fact not found";
    }


    @POST
    @Path("/quote/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addQuoteToUser(@PathParam("username")String userName, String quote){
        System.out.println("This Is Your Quote: " + quote);
        QuoteDTO quoteDTO = GSON.fromJson(quote, QuoteDTO.class);
        quoteDTO = INTERNAL_API_FACADE.createQuote(quoteDTO);
        INTERNAL_API_FACADE.addQuote(userName, quoteDTO.getId());
        System.out.println("Quote added to user");
        return Response.ok().entity(quoteDTO).build();
    }



//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String generateRandomFact() throws Exception {
//        ExternalApiFacade api = new ExternalApiFacade();
//        String returntext = String.valueOf(api.factAPI());
//        return GSON.toJson(returntext);
//    }

    @DELETE
    @Path("/delete/{username}/{quoteId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteQuoteFromUser(@PathParam("username") String userName, @PathParam("quoteId") int quoteId) {
        UserDTO userDTO = INTERNAL_API_FACADE.removeQuote(userName, quoteId);
        return Response.ok().entity(userDTO).build();
    }


}
