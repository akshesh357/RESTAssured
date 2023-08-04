package Hooks;

import io.cucumber.java.Before;
import main.utilities.RestAssuredExtension;

public class TestIntialize {

    @Before
    public void TestSetUp(){
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}
