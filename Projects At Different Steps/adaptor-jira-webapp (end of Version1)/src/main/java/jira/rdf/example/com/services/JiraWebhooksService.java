package jira.rdf.example.com.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import jira.rdf.example.com.JiraAdaptorManager;
import jira.rdf.example.com.resources.ChangeRequest;

@Path("jira/webhooks")
public class JiraWebhooksService
{
	@Context private HttpServletRequest httpServletRequest;
	@Context private HttpServletResponse httpServletResponse;
	@Context private UriInfo uriInfo;

	private static final Logger log = LoggerFactory.getLogger(JiraWebhooksService.class);

    public JiraWebhooksService()
    {
        super();
    }

    public ChangeRequest getChangeRequest(LinkedHashMap<String,Object> issueData) throws URISyntaxException
    {    
        ChangeRequest aChangeRequest = new ChangeRequest();

        aChangeRequest.setIdentifier((String) issueData.get("id"));
        LinkedHashMap<String,Object> issueFieldsData = (LinkedHashMap<String, Object>) issueData.get("fields");
        aChangeRequest.setDescription((String) issueFieldsData.get("description"));
        aChangeRequest.setTitle((String) issueFieldsData.get("summary"));
        LinkedHashMap<String,Object> projectData = (LinkedHashMap<String, Object>) issueFieldsData.get("project");
        String projectName = (String) projectData.get("name");
        aChangeRequest.setProject(projectName);
        LinkedHashMap<String,Object> creatorData = (LinkedHashMap<String, Object>) issueFieldsData.get("creator");
        aChangeRequest.setCreator((String) creatorData.get("name"));
        aChangeRequest.setAbout(ChangeRequest.constructURI("1", aChangeRequest.getIdentifier()));
        return aChangeRequest;
    }

    @POST
    @Path("issues")
    @Produces({ MediaType.TEXT_HTML })
    public Response handleIssuesWebhooks() throws ServletException, IOException, URISyntaxException
    {    
        ServletInputStream inputStream = httpServletRequest.getInputStream();
        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> jsonData = new HashMap<String,Object>();
        jsonData = mapper.readValue(inputStream, Map.class);

        LinkedHashMap<String,Object> issueData = (LinkedHashMap<String, Object>) jsonData.get("issue");
        try {
            //Transform the Json Issue into a ChangeRequest resource.
            ChangeRequest aChangeRequest = getChangeRequest(issueData);
            //Save the ChangeRequest resource into a triplestore.
            JiraAdaptorManager.store.updateResources(new URI ("urn:x-arq:DefaultGraph"), aChangeRequest);
        } catch (Exception e) {        
            log.error("Failed to update an Issues resource", e);
        } 

        return Response.ok().build();
    }

}
