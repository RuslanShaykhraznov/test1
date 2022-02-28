/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchposts.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import searchposts.searchposts.api.Searcher;

/**
 *
 * @author Ruslan
 */
@Path("search")
public class JerseyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHTML(@QueryParam("subject") String subject) throws Exception {
        Searcher searcher = new Searcher();
        return searcher.getPostsBySubject(subject);
    }
}
