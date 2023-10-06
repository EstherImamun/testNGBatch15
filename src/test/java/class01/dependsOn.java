package class01;

import org.testng.annotations.Test;

public class dependsOn {
    @Test
    public void Login(){
        //System.out.println(6/0); we can use this if we want to create an error example. this will give you an exception
        System.out.println("I am here");
    }

    @Test(dependsOnMethods = {"Login"})
    public void DashBoardVerification(){
        System.out.println("after login i am verifying dashboard");
    }
}
