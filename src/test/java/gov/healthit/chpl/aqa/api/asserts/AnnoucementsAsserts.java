package gov.healthit.chpl.aqa.api.asserts;

import java.util.List;
import org.testng.Assert;
import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.api.stepDefinitions.AnnoucementsTest;
import gov.healthit.chpl.aqa.api.stepDefinitions.ComplaintsTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AnnoucementsAsserts {
    private Response response;
    public static int postAnnoucementId;
    private List<Integer> idList;
    private JsonPath js;

    @Then("^I extract annoucement id from response body$")
    public void extractAnnoucementid() {
        js = AnnoucementsTest.response.jsonPath();
        postAnnoucementId = js.get("id");
    }
    @Then("^I extract ids of all announcements from response body$")
    public void extractIdsOfAllAnnouncements() {
        idList = AnnoucementsTest.response.jsonPath().getList("announcements.id");
    }

    @Then("^I validate posted id exist in ids of all announcements$")
    public void validatePostedidInAnnouncements() {
        Assert.assertTrue(idList.contains(postAnnoucementId));
    }

    @Then("^I validate response body has updated annoucement information$")
    public void validateUpdatedAnnoucement() {
        js = AnnoucementsTest.response.jsonPath();
        String updatedTitle = "Updated Title- API test annoucement";
        Assert.assertEquals(js.getString("title"), updatedTitle);
    }

    @Then("^I validate response says \"([^\"]*)\"$")
    public void validateNotFound(String errorMessage) {
        js = AnnoucementsTest.response.jsonPath();
        Assert.assertEquals(js.getString("error"), errorMessage);
    }

    @Then("^I verify status code \"([^\"]*)\" for annoucement end point response$")
    public void validateStatusCode(int statuscode) {
        response = AnnoucementsTest.response.then().assertThat().statusCode(statuscode).extract().response();
    }

}
