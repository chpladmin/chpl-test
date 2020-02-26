package gov.healthit.chpl.aqa.api.asserts;

import java.util.List;
import org.testng.Assert;
import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.api.stepDefinitions.AnnoucementsTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AnnoucementsAsserts {
    private Response response;
    private static int postAnnoucementId;
    private List<Integer> idList;
    private JsonPath js;

    @Then("^I extract announcement id from response body$")
    public void extractAnnoucementid() {
        js = AnnoucementsTest.getResponse().jsonPath();
        setPostAnnoucementId(js.get("id"));
    }
    @Then("^I extract ids of all announcements from response body$")
    public void extractIdsOfAllAnnouncements() {
        idList = AnnoucementsTest.getResponse().jsonPath().getList("announcements.id");
    }

    @Then("^I validate posted id exist in ids of all announcements$")
    public void validatePostedidInAnnouncements() {
        Assert.assertTrue(idList.contains(getPostAnnoucementId()));
    }

    @Then("^I validate response body has updated announcement information$")
    public void validateUpdatedAnnoucement() {
        js = AnnoucementsTest.getResponse().jsonPath();
        String updatedTitle = "Updated Title- API test annoucement";
        Assert.assertEquals(js.getString("title"), updatedTitle);
    }

    @Then("^I validate response says \"([^\"]*)\"$")
    public void validateNotFound(String errorMessage) {
        js = AnnoucementsTest.getResponse().jsonPath();
        Assert.assertEquals(js.getString("error"), errorMessage);
    }

    @Then("^I verify status code \"([^\"]*)\" for announcement end point response$")
    public void validateStatusCode(int statuscode) {
        response = AnnoucementsTest.getResponse().then().assertThat().statusCode(statuscode).extract().response();
    }
    public static int getPostAnnoucementId() {
        return postAnnoucementId;
    }
    public static void setPostAnnoucementId(int postAnnoucementId) {
        AnnoucementsAsserts.postAnnoucementId = postAnnoucementId;
    }

}
