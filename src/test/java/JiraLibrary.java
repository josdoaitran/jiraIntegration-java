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
    private static String userNameJira = "doaitran";
    private static String passWordJira = "Changeit@123";
    public static String jiraProjectName = "ATD";

    private static String hostJira = new String("http://192.168.1.111:8080");
    private static String projectAPI = hostJira + "/rest/api/2/project";
    private static String createTicketAPI = hostJira + "/rest/api/2/issue";
    private static String updateTicketAPI = hostJira + "/rest/api/2/issue";
    private static String deleteTicketAPI = hostJira + "/rest/api/2/issue";
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

    public static String createJiraTicket() throws AuthenticationException {
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
        String createIssueData = "{\"fields\":{\"project\":{\"key\":\""+projectKey+"\"},\"summary\":\"REST Test\",\"issuetype\":{\"name\":\"Bug\"}}}";
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
        String createIssueData = "{\"fields\":{\"project\":{\"key\":\"DEMO\"},\"summary\":\"REST Test\",\"issuetype\":{\"name\":\"Bug\"}}}";
        String issue = invokePostMethod(auth, updateTicketAPI, createIssueData);
        System.out.println(issue);
        JSONObject issueObj = new JSONObject(issue);
        String newKey = issueObj.getString("key");
        System.out.println("Key:"+newKey);
    }

    private static void deleteJiraTicket(String jirTicket) throws AuthenticationException {
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
        invokeDeleteMethod(auth, deleteTicketAPI+"/"+jirTicket);
    }

    private static String invokeGetMethod(String auth, String url) throws AuthenticationException, ClientHandlerException {
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

    private static String invokePostMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
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

    private static void invokePutMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").put(ClientResponse.class, data);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
    }

    private static void invokeDeleteMethod(String auth, String url) throws AuthenticationException, ClientHandlerException {
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
