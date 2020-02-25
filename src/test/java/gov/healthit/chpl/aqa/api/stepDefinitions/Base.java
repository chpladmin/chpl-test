package gov.healthit.chpl.aqa.api.stepDefinitions;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/** Base class for step definition files. */
public class Base {

    private static String url = System.getProperty("url");
    private static String apikey = System.getProperty("apikey");
    private static String username;
    private static String password;
    private static final int HTTP_GOOD_RESPONSE = 200;
    private static RequestSpecification request;
    private static Response response;

    /** Default constructor. */
    public Base() {

    }

    public static String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public static  String getApikey() {
        return apikey;
    }

    public void setApikey(final String apikey) {
        this.apikey = apikey;
    }

    public static Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
    public static String getAuth(String role) {
        switch (role) {
        case "ROLE_ADMIN":
            username = System.getProperty("roleAdminUsername");
            password = System.getProperty("roleAdminPassword");
            break;
        case "ROLE_ACB":
            username = System.getProperty("roleAcbUsername");
            password = System.getProperty("roleAcbPassword");
            break;
        case "ROLE_ONC":
            username = System.getProperty("roleOncUsername");
            password = System.getProperty("roleOncPassword");
            break;
        default:
            username = System.getProperty("roleAdminUsername");
            password = System.getProperty("roleAdminPassword");
            break;
        }

        RestAssured.baseURI = Base.getUrl();
        Response res = given()
                .header("API-KEY", Base.getApikey())
                .header("content-type", "application/json")
                .body("{\r\n"
                        + "  \"password\": \"" + password + "\",\r\n"
                        + "  \"userName\": \"" + username + "\"\r\n"
                        + "}")
                .when()
                .post("rest/auth/authenticate")
                .then().assertThat().statusCode(HTTP_GOOD_RESPONSE).and().contentType(ContentType.JSON)
                .extract().response();
        JsonPath js = res.jsonPath();
        String token = js.get("token");
        String auth = "Bearer " + token;
        return auth;
    }

    public static RequestSpecification setValidHeaders(String role) {
        request = given().header("API-KEY", Base.getApikey()).header("content-type", "application/json")
                .header("Authorization", Base.getAuth(role));
        return request;
    }

    public static Response sendRequest(String method, String resource, RequestSpecification request) {
        if (method.equalsIgnoreCase("post")) {
            response = request.when().post(resource);
            } else if (method.equalsIgnoreCase("get")) {
            response = request.when().get(resource);
            } else if (method.equalsIgnoreCase("put")) {
            response = request.when().put(resource);
            } else if (method.equalsIgnoreCase("delete")) {
            response = request.when().delete(resource);
            }
        return response;
    }

    public static Response sendRequestWithId(String method, int id, String pathparam, String resource) {
        if (method.equalsIgnoreCase("get")) {
            response = request.pathParams(pathparam, id).when().get(resource);
            } else if (method.equalsIgnoreCase("put")) {
            response = request.pathParams(pathparam, id).when().put(resource);
            } else if (method.equalsIgnoreCase("delete")) {
            response = request.pathParams(pathparam, id).when().delete(resource);
            }
        return response;
    }

    public static RequestSpecification setRequestBody(String method, String postPayload, String putPayload) {
        if (method.equalsIgnoreCase("POST")) {
            request.body(postPayload);
            } else if (method.equalsIgnoreCase("PUT")) {
            request.body(putPayload);
            }
        return request;
    }

}
