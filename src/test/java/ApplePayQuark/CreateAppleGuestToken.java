package ApplePayQuark;

import com.sun.net.httpserver.Authenticator;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;


public class CreateAppleGuestToken {


    //  creating apple guest token
    public static String createAGToken(){
        //specify base URI
        RestAssured.baseURI ="https://quark.prd.shoprunner.io/public/nonmember/token" ;

        // creating request object
        RequestSpecification httpRequest = RestAssured.given();

        // Request paramerters sending along POST request
        JSONObject requestParams = new JSONObject();
        requestParams.put( "user_agent" , "fakeUserAgent");
        requestParams.put( "sr_browser_id" , "afe0f7c1-64d4-45ff-9c0c-4479d3fcd626");
        requestParams.put( "retailer_code" , "TOMMY");
        requestParams.put( "ip_address" , "34.236.252.22");
        requestParams.put( "domain_session_id" , "6b18d549-8299-4e74-8dda-bd768a23cbd3");
        requestParams.put( "id" , null);
        requestParams.put( "token_status_code" , null);
        requestParams.put( "order_email" , null);
        requestParams.put( "merchant_order_id" , null);
        requestParams.put( "member_id" , null);

        // adding header
        httpRequest.header("Content-Type","application/json");

        //attach the above data to the request
        httpRequest.body(requestParams.toJSONString());

        //sending the REQUEST
        Response response= httpRequest.request(Method.POST,"");

        // print response in console window
        System.out.println("Create Apple Guest Token Response Body is: ");
        String responseBody = response.getBody().prettyPrint() ;

        // status code
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: "+ statusCode);

        Assert.assertEquals(201, statusCode);

        String  authToken= response.jsonPath().get("data.auth_token");
        System.out.println("auth_token is: " + authToken);

        return authToken;
    }




}
