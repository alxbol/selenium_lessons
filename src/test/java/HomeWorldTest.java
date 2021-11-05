import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeWorldTest {

    @Test
    public void homeWorldCheck(){
        String str = "Home World!";
        Assert.assertEquals(str, "Home World");
    }
}
