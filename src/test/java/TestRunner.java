import org.junit.Test;

import javax.naming.AuthenticationException;
import java.text.ParseException;
import java.util.Date;


public class TestRunner {

    @Test
    public void TestExample() throws AuthenticationException, ParseException {
        //JiraLibrary.getJiraProjectName();
        //SynapseRTLibrary.addTestCycleToTestPlan("ATD-7","TestCycleName");
        //JiraLibrary.deleteJiraTicket("ATD-2");
        //JiraLibrary.createJiraTaskTicket();
        //SynapseRTLibrary.addTestCaseToTestPlan("ATD-6","ATD-7");
        SynapseRTLibrary.updateTestCycleStatus("ATD-7","TestCycleName","Start");
    }



}
