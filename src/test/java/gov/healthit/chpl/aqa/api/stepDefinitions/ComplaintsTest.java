package gov.healthit.chpl.aqa.api.stepDefinitions;

import static io.restassured.RestAssured.given;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import gov.healthit.chpl.aqa.api.asserts.ComplaintsAsserts;
import gov.healthit.chpl.aqa.api.payLoad.ComplaintsPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ComplaintsTest {

    public static Response response;
    private RequestSpecification request;

    @Given("^I set base URL$")
    public void setURL() {
        RestAssured.baseURI = Base.getUrl();
    }

    @Given("^I set Headers with API key and (.+)$")
    public void setHeaders(String authorization) {
        if (authorization.contentEquals("Valid")) {
            request = given().header("API-KEY", Base.getApikey()).header("content-type", "application/json")
                    .header("Authorization", Base.getAuth("ROLE_ADMIN"));
        } else if (authorization.contentEquals("Invalid")) {
            request = given().header("API-KEY", Base.getApikey()).header("content-type", "application/json");
        }
    }

    @When("^I send \"([^\"]*)\" request to complaints resource \"([^\"]*)\"$")
    public void sendGetRequest(String method, String resource) {
        response = Base.sendRequest(method, resource, request);
    }

    @Given("^I set Headers with API key, \"([^\"]*)\" authorization for complaints$")
    public void setHeader(String role) {
        request = Base.setValidHeaders(role);
    }

    @Given("^I set \"([^\"]*)\" complaints request body$")
    public void setRequestBody(String method) {
        request = Base.setRequestBody(method, ComplaintsPayload.postPayload(), ComplaintsPayload.putPayload());
    }

//    @When("^I send POST request to resource \"([^\"]*)\"$")
//    public void sendPostRequest(String resource) {
//        response = request.when().post(resource);
//    }

    @When("^I send \"([^\"]*)\" request to resource \"([^\"]*)\" with posted complaintId$")
    public void sendPutRequest(String method, String resource) {
        response = Base.sendRequestWithId(method, ComplaintsAsserts.postComplaintId, "complaintId", resource);
    }

//    @When("^I send DELETE request to resource \"([^\"]*)\"$")
//    public void sendDeleteRequest(String resource) {
//        response = request.pathParams("complaintId", ComplaintsAsserts.postComplaintId).when().delete(resource);
//    }
}
