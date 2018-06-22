import org.junit.Test;

import javax.naming.AuthenticationException;
import java.util.Date;


public class TestRunner {

    @Test
    public void TestExample() throws AuthenticationException {
        //JiraLibrary.getJiraProjectName();
        JiraLibrary.createJiraTicket("Bug");
        //JiraLibrary.deleteJiraTicket("ATD-2");
        //JiraLibrary.createJiraTaskTicket();
        //SynapseRTLibrary.addTestCaseToTestPlan("ATD-6","ATD-7");
    }


    public static String generateJiraTicketDate(){
        Date jiraTicketDate;
        jiraTicketDate = new Date();
        return jiraTicketDate.toString();
    }
}
