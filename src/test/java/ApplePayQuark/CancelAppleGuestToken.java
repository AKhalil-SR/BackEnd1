package ApplePayQuark;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class CancelAppleGuestToken extends CreateAppleGuestToken{

    static String auth_token= createAGToken();
    public static void cancelAGToken(){


        RestAssured.baseURI="https://quark.prd.shoprunner.io/public/nonmember/token";
        RequestSpecification httpRequest= RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put( "user_agent" , "fakeUserAgent");
        requestParams.put( "sr_browser_id" , "afe0f7c1-64d4-45ff-9c0c-4479d3fcd626");
        requestParams.put( "retailer_code" , "TOMMY");
        requestParams.put( "ip_address" , "34.236.252.22");
        requestParams.put( "domain_session_id" , "6b18d549-8299-4e74-8dda-bd768a23cbd3");
        requestParams.put( "id" , auth_token);
        requestParams.put( "token_status_code" , null);
        requestParams.put( "order_email" , null);
        requestParams.put( "merchant_order_id" , null);
        requestParams.put( "member_id" , null);

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.request(Method.DELETE, auth_token);
        System.out.println("------>> Cancel Apple Guest Token Response Body is: ");
        String responseBody = response.getBody().prettyPrint();
        int statusCode= response.getStatusCode();
        System.out.println("Status Code is: "+ statusCode);
        Assert.assertEquals(200, statusCode);
        String tokenStatusCode = response.jsonPath().get("data.token_status_code");
        Assert.assertEquals("session_cancelled",tokenStatusCode);
        System.out.println("Token Status Code is: " +tokenStatusCode);




    }



}
