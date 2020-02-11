package gov.healthit.chpl.aqa.api.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.healthit.chpl.aqa.api.asserts.AnnoucementsAsserts;
import gov.healthit.chpl.aqa.api.asserts.ComplaintsAsserts;
import gov.healthit.chpl.aqa.api.payLoad.AnnouncementsPayload;
import gov.healthit.chpl.aqa.api.payLoad.ComplaintsPayload;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AnnoucementsTest {

    public static Response response;
    private RequestSpecification request;

    @When("^I send \"([^\"]*)\" request to annoucement resource \"([^\"]*)\"$")
    public void sendRequestToAnnoucementResource(String method, String resource) {
        response = Base.sendRequest(method, resource, request);
    }

    @Given("^I set Headers with API key, \"([^\"]*)\" authorization for announcements$")
    public void setHeadersForAnnouncements(String role) throws Throwable {
        request = Base.setValidHeaders(role);
    }

    @Given("^I set \"([^\"]*)\" announcements request body$")
    public void setAnnouncementsRequestBody(String method) throws Throwable {
        if (method.contains("POST")) {
            request.body(AnnouncementsPayload.postPayload());
        } else if (method.contains("PUT")) {
            request.body(AnnouncementsPayload.putPayload());
        }
    }

    @When("^I send \"([^\"]*)\" request to resource \"([^\"]*)\" with posted annoucementId$")
    public void sendRequestToAnnoucementResourceWithId(String method, String resource) {
        response = Base.sendRequestWithId(method, AnnoucementsAsserts.postAnnoucementId, "announcementId", resource);
    }

}
