@RegressionAPI
Feature: Complaint-controller endpoints
  Verify CRUD operations on Complaint-controller endpoints
 
Background: Setting base URL
    Given I set base URL

  Scenario Outline: Verify status codes based on user's authorization

    Given I set Headers with API key and <Authorization>
    When I send "GET" request to complaints resource "rest/complaints"
    Then I verify the <Status Code> for complaints controller
    Examples: 
      | Authorization | Status Code |
      | Invalid       |         401 |
      | Valid         |         200 |

  Scenario: Verify adding, updating and deleting a complaint
    Given I set Headers with API key, "ROLE_ACB" authorization for complaints 
    And I set "POST" complaints request body
    When I send "POST" request to complaints resource "rest/complaints" 
    Then I verify status code "200" for complaints end point response 
    And I extract complaint id from response body
    Given I set Headers with API key, "ROLE_ACB" authorization for complaints
    When I send "GET" request to complaints resource "rest/complaints" 
    Then I extract ids of all complaints from response body
    And I validate posted id exist in ids of all complaints
    Given I set Headers with API key, "ROLE_ACB" authorization for complaints 
    And I set "PUT" complaints request body
    When I send "PUT" request to resource "rest/complaints/{complaintId}" with posted complaintId
    Then I verify status code "200" for complaints end point response 
    And I validate response body has updated ONC complaint id
    Given I set Headers with API key, "ROLE_ACB" authorization for complaints
    When I send "DELETE" request to resource "rest/complaints/{complaintId}" with posted complaintId 
    Then I verify status code "200" for complaints end point response 
    Given I set Headers with API key, "ROLE_ACB" authorization for complaints
    When I send "GET" request to complaints resource "rest/complaints"  
    Then I extract ids of all complaints from response body
    And I validate posted id doesnt exist in ids of all complaints
