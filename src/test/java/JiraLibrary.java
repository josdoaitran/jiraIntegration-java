/*

 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.naming.AuthenticationException;
import java.util.Date;

public class JiraLibrary {
    public static String userNameJira = "doaitran";
    public static String passWordJira = "Changeit@123";
    public static String jiraProjectName = "ATD";
    public static String summaryTicket = "Here is Summary of ticket";

    public static String hostJira = new String("http://192.168.1.111:8080");
    public static String projectAPI = hostJira + "/rest/api/2/project";
    public static String createTicketAPI = hostJira + "/rest/api/2/issue";
    public static String updateTicketAPI = hostJira + "/rest/api/2/issue";
    public static String deleteTicketAPI = hostJira + "/rest/api/2/issue";
    /**
     * This Method to get Jira Project Name
      * @return => projectKey as: ATD
     * @throws AuthenticationException
     */
    public static String getJiraProjectName() throws AuthenticationException {
        String auth = new String(Base64.encode(userNameJira + ":" + passWordJira));
        String projects = invokeGetMethod(auth, projectAPI);
        System.out.println(projects);
        String projectKey = null;
        JSONArray projectArray = new JSONArray(projects);
        for (int i = 0; i < projectArray.length(); i++) {
            JSONObject proj = projectArray.getJSONObject(i);
            System.out.println("Key:"+proj.getString("key")+", Name:"+proj.getString("name"));
            projectKey = proj.getString("key");
        }
        System.out.println("Project Key: "+projectKey);
        return projectKey;
    }

    /**
     *
     * @param ticketType : Bug; TestCase
     * @return
     * @throws AuthenticationException
     */

    public static String createJiraTicket(String ticketType) throws AuthenticationException {
        String auth = new String(Base64.encode(userNameJira + ":" + passWordJira));
        String projects = invokeGetMethod(auth, projectAPI);
        System.out.println(projects);
        String projectKey = null;
        JSONArray projectArray = new JSONArray(projects);
        for (int i = 0; i < projectArray.length(); i++) {
            JSONObject proj = projectArray.getJSONObject(i);
            System.out.println("Key:"+proj.getString("key")+", Name:"+proj.getString("name"));
            projectKey = proj.getString("key");
        }
        String createIssueData = "{\"fields\":{\"project\":{\"key\":\""+projectKey+"\"},\"summary\":\""+summaryTicket+"\",\"issuetype\":{\"name\":\""+ticketType+"\"}}}";
        String issue = invokePostMethod(auth, createTicketAPI, createIssueData);
        System.out.println("Bug Ticket: "+issue);
        JSONObject issueObj = new JSONObject(issue);
        String newKey = issueObj.getString("key");
        System.out.println("Key:"+newKey);
        return issue;
    }

    private static void updateJiraTicket() throws AuthenticationException {
        String auth = new String(Base64.encode(userNameJira + ":" + passWordJira));
        String projects = invokeGetMethod(auth, projectAPI);
        System.out.println(projects);
        String projectKey = null;
        JSONArray projectArray = new JSONArray(projects);
        for (int i = 0; i < projectArray.length(); i++) {
            JSONObject proj = projectArray.getJSONObject(i);
            System.out.println("Key:"+proj.getString("key")+", Name:"+proj.getString("name"));
            projectKey = proj.getString("key");
        }
        String createIssueData = "{\"fields\":{\"project\":{\"key\":\""+projectKey+"\"},\"summary\":\""+summaryTicket+"\",\"issuetype\":{\"name\":\"Bug\"}}}";
        String issue = invokePostMethod(auth, updateTicketAPI, createIssueData);
        System.out.println(issue);
        JSONObject issueObj = new JSONObject(issue);
        String newKey = issueObj.getString("key");
        System.out.println("Key:"+newKey);
    }

    public static void deleteJiraTicket(String jirTicket) throws AuthenticationException {
        String auth = new String(Base64.encode(userNameJira + ":" + passWordJira));
        invokeDeleteMethod(auth, deleteTicketAPI+"/"+jirTicket);
    }

    public static String invokeGetMethod(String auth, String url) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").get(ClientResponse.class);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return response.getEntity(String.class);
    }

    public static String invokePostMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").post(ClientResponse.class, data);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return response.getEntity(String.class);
    }

    public static void invokePutMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").put(ClientResponse.class, data);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
    }

    public static void invokeDeleteMethod(String auth, String url) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").delete(ClientResponse.class);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
    }
}
