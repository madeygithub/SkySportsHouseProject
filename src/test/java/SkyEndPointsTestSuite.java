import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.testng.log4testng.Logger;
import org.apache.logging.log4j.*;


public class SkyEndPointsTestSuite {

    //Logger log = Logger.getLogger(SkyEndPointsTestSuite.class);
    private static Logger log = LogManager.getLogger(SkyEndPointsTestSuite.class.getClass());

    // Test case to Test if the GET request on the given URI will return the Response body.
    @Test(dataProvider = "data-provider")
    public void testGetEndPoint(String endPointURI, int endPoint)
    {
        RestAssured.baseURI = endPointURI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET);
        String responseBody = response.getBody().asString();
        System.out.println("The Response Body Endpoint" + endPoint +" is :" + responseBody);
        log.info("The Response Body of EndPoint "+endPoint+" is displayed." + responseBody );
    }

    // Test case to Test if the POST Request on the given URI will return the error Status code.
    @Test(dataProvider = "data-provider")
    public void testPOSTEndPoint(String endPointURI, int endPoint)
    {
        RestAssured.baseURI = endPointURI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.POST);
        int statusCode = response.statusCode();
        Assert.assertTrue(statusCode >= 400 , "The Request returns an Error code.");
        System.out.println("The Endpoint " + endPoint+ " POST statusCode is " + statusCode);
        log.error("The Statuscode of Endpoint" + endPoint+" POST Request is displayed. " + statusCode );
    }

    // Test case to Test if the PUT Request on the given URI will return the error Status code.
    @Test(dataProvider = "data-provider")
    public void testPUTEndPoint(String endPointURI, int endPoint)
    {
        RestAssured.baseURI = endPointURI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.PUT);
        int statusCode = response.statusCode();
        Assert.assertTrue(statusCode >= 400 , "The Request returns an Error code.");
        System.out.println("The Endpoint" + endPoint + " PUT statusCode is " + statusCode);
        log.error("The Statuscode of Endpoint" + endPoint +" PUT Request is displayed." + statusCode);
    }

    // Test case to Test if the DELETE Request on the given URI will return the error Status code.
    @Test(dataProvider = "data-provider")
    public void testDELETEEndPoint(String endPointURI, int endPoint)
    {
        RestAssured.baseURI = endPointURI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.DELETE);
        int statusCode = response.statusCode();
        Assert.assertTrue(statusCode >= 400 , "The Request returns an Error code.");
        System.out.println("The Endpoint" + endPoint+ " DELETE statusCode is " + statusCode);
        log.error("The Statuscode of Endpoint "+ endPoint+ " DELETE Request  is displayed." + statusCode);
    }

    //Dataprovider to repeat the testcases for the two URIs
    @DataProvider(name = "data-provider")
    public Object[][] dpMethod(){
        return new Object[][] {{"https://5f99522350d84900163b8737.mockapi.io/tech-test/articles",1}, {"https://5f99522350d84900163b8737.mockapi.io/tech-test/articles/2",2}};
    }

}
