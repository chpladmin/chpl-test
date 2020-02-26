@RegressionAPI
Feature: Announcement-controller endpoints
  Verify CRUD operations on announcement-controller endpoints
  
Background: Setting base URL
    Given I set base URL

  Scenario: Verify adding, updating and deleting a announcement
    Given I set Headers with API key, "ROLE_ADMIN" authorization for announcements 
    And I set "POST" announcements request body
    When I send "POST" request to announcement resource "rest/announcements" 
    Then I verify status code "200" for announcement end point response 
    And I extract announcement id from response body
    Given I set Headers with API key, "ROLE_ADMIN" authorization for announcements
    When I send "GET" request to announcement resource "rest/announcements" 
    Then I extract ids of all announcements from response body
    And I validate posted id exist in ids of all announcements
    Given I set Headers with API key, "ROLE_ADMIN" authorization for announcements 
    And I set "PUT" announcements request body
    When I send "PUT" request to resource "rest/announcements/{announcementId}" with posted annoucementId
    Then I verify status code "200" for announcement end point response 
    And I validate response body has updated announcement information
    Given I set Headers with API key, "ROLE_ADMIN" authorization for announcements 
    When I send "DELETE" request to resource "rest/announcements/{announcementId}" with posted annoucementId 
    Then I verify status code "200" for announcement end point response 
    Given I set Headers with API key, "ROLE_ACB" authorization for announcements
    When I send "GET" request to resource "rest/announcements/{announcementId}" with posted annoucementId 
    Then I verify status code "404" for announcement end point response 
    And I validate response says "Announcement not found."
