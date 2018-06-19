import org.junit.Test;

import javax.naming.AuthenticationException;
import java.util.Date;


public class TestRunner {

    @Test
    public void TestExample() throws AuthenticationException {
        //JiraLibrary.getJiraProjectName();
        JiraLibrary.createJiraTicket();
    }


    public static String generateJiraTicketDate(){
        Date jiraTicketDate;
        jiraTicketDate = new Date();
        return jiraTicketDate.toString();
    }
}
