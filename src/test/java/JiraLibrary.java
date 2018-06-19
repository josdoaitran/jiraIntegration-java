/*

 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.Base64;

import javax.naming.AuthenticationException;
import java.util.Date;

public class JiraLibrary {
    public static String userNameJira = "doaitran";
    public static String passWordJira = "Changeit@123";
    public static String jiraProjectName = "ATD";

    public static String hostJira = new String("http://192.168.1.111:8080");
    public static String createTicketAPI = hostJira + "/rest/api/2/project";

    public static String createATicket(String scriptStartTime){
        String auth = new String(Base64.encode(userNameJira + ":" + passWordJira));

        Client client = Client.create();

        WebResource webResource = client.resource(createTicketAPI);

        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").get(ClientResponse.class);

        String output = response.getEntity(String.class);
        System.out.println("JIRA Server returns:\n" + output);
        return output;
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
