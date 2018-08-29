package tests.suites;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import tests.reqresapitests.*;

@Ignore
@RunWith(value=Suite.class)
@SuiteClasses({TestGetListUsers.class, TestGetSingleUser.class, TestPostForCreatingAUser.class,
        TestPostToRegisterAUser.class, TestWithRestAssured.class})
public class TestTheReqresAPI {
    //
}
