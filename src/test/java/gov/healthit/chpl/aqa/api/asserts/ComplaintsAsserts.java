package gov.healthit.chpl.aqa.api.asserts;

import java.util.List;
import org.testng.Assert;
import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.api.stepDefinitions.ComplaintsTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ComplaintsAsserts {
    private Response response;
    private static int postComplaintId;
    private List<Integer> idList;
    private JsonPath js;

    @Then("^I verify the (.+) for complaints controller$")
    public void apiReturnsCorrectStatusCode(int statuscode) throws Throwable {
        Assert.assertEquals(ComplaintsTest.getResponse().getStatusCode(), statuscode);
    }

    @Then("^I verify status code \"([^\"]*)\" for complaints end point response$")
    public void validateStatusCode(int statuscode) throws Throwable {
        response = ComplaintsTest.getResponse().then().assertThat().statusCode(statuscode).extract().response();
    }

    @Then("^I extract complaint id from response body$")
    public void extractpostResponseBody() throws Throwable {
        js = ComplaintsTest.getResponse().jsonPath();
        setPostComplaintId(js.get("id"));
    }

    @Then("^I extract ids of all complaints from response body$")
    public void extractgetResponseBody() throws Throwable {
        idList = ComplaintsTest.getResponse().jsonPath().getList("results.id");
    }

    @Then("^I validate posted id exist in ids of all complaints$")
    public void validatePostComplaint() throws Throwable {
        Assert.assertTrue(idList.contains(getPostComplaintId()));
    }

    @Then("^I validate response body has updated ONC complaint id$")
    public void validatePutResponse() throws Throwable {
        js = response.jsonPath();
        String updatedvalue = "ONC-Updated";
        Assert.assertEquals(js.getString("oncComplaintId"), updatedvalue);
    }

    @Then("^I validate posted id doesnt exist in ids of all complaints$")
    public void validateCompliantsDeletion() throws Throwable {
        Assert.assertFalse(idList.contains(getPostComplaintId()));
    }

    public static int getPostComplaintId() {
        return postComplaintId;
    }

    public void setPostComplaintId(int postComplaintId) {
        this.postComplaintId = postComplaintId;
    }
}
