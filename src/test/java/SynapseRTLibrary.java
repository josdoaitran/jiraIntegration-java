import com.sun.jersey.core.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.naming.AuthenticationException;

/*
SynapseRT library:
https://doc.go2group.com/display/SRT6/synapseRT+REST+API

 */
public class SynapseRTLibrary extends JiraLibrary{
    private static String testPlanAPI = hostJira + "/rest/synapse/latest/public/testPlan/";

    public static void addTestCaseToTestPlan(String testCaseID, String testPlanID) throws AuthenticationException {
        String auth = new String(Base64.encode(userNameJira + ":" + passWordJira));
        String createIssueData = "{\"testCaseKeys\":[\""+testCaseID+"\"]}";
        String issue = invokePostMethod(auth, testPlanAPI+testPlanID+"/addMembers", createIssueData);
        if(issue.contains("Success")){
            System.out.println("Added successfully.");
        }else {
            System.out.println("Unable to add TCs: " + testCaseID + "on TestPlan: " + testPlanID);
        }
    }

}
