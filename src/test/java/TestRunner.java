import org.junit.Test;

import java.util.Date;


public class TestRunner {

    @Test
    public void TestExample() {
        JiraLibrary.createATicket(generateJiraTicketDate());
    }


    public static String generateJiraTicketDate(){
        Date jiraTicketDate;
        jiraTicketDate = new Date();
        return jiraTicketDate.toString();
    }
}
