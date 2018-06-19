import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(Test.class)
public class Test {

    @Test
    public void Test1() {
        createATicket(generateJiraTicketDate());
    }


    public static String generateJiraTicketDate(){
        Date jiraTicketDate;
        jiraTicketDate = new Date();
        return jiraTicketDate.toString();
    }
}
