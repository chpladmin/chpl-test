Feature: Download chpl data files
  To test download files functionality of CHPL application

  Scenario:  User is not logged in, 7 files available
    Given user is on CHPL download page
    Then user sees 7 download files

  Scenario: Each download file has associated definition file
    Given user is on CHPL download page
    When user selects a file in download file box
    Then definition file shows based on download file selection