import org.junit.Test;

import javax.naming.AuthenticationException;
import java.text.ParseException;
import java.util.Date;


public class TestRunner {

    @Test
    public void TestExample() throws AuthenticationException, ParseException {
        //JiraLibrary.getJiraProjectName();
        //JiraLibrary.deleteJiraTicket("ATD-2");
        //JiraLibrary.createJiraTaskTicket();
        //SynapseRTLibrary.addTestCaseToTestPlan("ATD-6","ATD-7");
        //SynapseRTLibrary.updateTestCycleStatus("ATD-7","TestCycleName","Start");
        SynapseRTLibrary.updateTestCaseInTestCycle("ATD-11","Failed","ATD-7","TestCycleName");
    }



}
