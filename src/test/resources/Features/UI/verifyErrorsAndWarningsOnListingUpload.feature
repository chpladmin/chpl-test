@listingUpload
@Regression
Feature: Verify expected errors and warnings display on inspect screen after litsing upload
         Sometimes upload files have invalid inputs that should not be accepted and saved after listing confirm. Invalid values are removed where applicable, errors and warnings are shown to users for data removal. 
         OCD-2558-Verify listing upload with duplicate macra measure value shows warning on inspect and confirm succeeds
         OCD-2593-Verify warnings for duplicate values in criteria detail fields in upload template
         OCD-2635-Show better error message when user uploads Test Tasks with IDs that are too long
         OCD-2640-Verify warning for bad data in Targeted User field
         OCD-2697-Handle bad characters on upload in number fields
         OCD-2562-Retire a Test Tool
         OCD-2559-Handle invalid certificaion date in upload file
         OCD-3110-Display better error message on upload when QMS Standard exceeds allowed length
            
  Scenario Outline: Verify warnings for duplicate input values in applicable fields on upload-inspect for 2015 listings
    Given I'm logged in as "ROLE_ACB"
    And I am on Upload Listings page
    When I upload a "2015" listing with CHPL ID "15.05.05.1447.SLI1.v1.00.1.180707"
    Then I see upload successful message
    When I go to Confirm Pending Products Page
    And I open inspect form to inspect listing details
    Then I see warning: "<warning>" for duplicate value in "<field>"
  Examples:
    |warning|field|
    |Certification 170.315 (a)(1) contains duplicate Additional Software: Non CP Source: 'InterSystems Cache', Version 'v2', Grouping 'b'. The duplicates have been removed.|Additional Software|
    |Certification 170.315 (a)(1) contains duplicate G1 Macra Measure: 'GAP-EP'. The duplicates have been removed.|G1 Macra Measure|
    |Certification 170.315 (a)(1) contains duplicate G2 Macra Measure: 'GAP-EP'. The duplicates have been removed.|G2 Macra Measure|
    |Certification 170.315 (a)(1) contains duplicate Test Functionality: Number '(a)(1)(ii)'. The duplicates have been removed.|Functionality Tested|
    |Certification 170.315 (a)(1) contains duplicate Test Procedure: Name 'ONC Test Method', Version '1'. The duplicates have been removed.|Test Procedure|
    |Certification 170.315 (b)(1) contains duplicate Test Data: Name 'ONC Test Method', Version '1'. The duplicates have been removed.|Test Data|    
    |Certification 170.315 (b)(1) contains duplicate Test Standard: Number '170.207(i)'. The duplicates have been removed.|Test Standard|
    |Certification 170.315 (b)(1) contains duplicate Test Tool: Name 'HL7 CDA Cancer Registry Reporting Validation Tool', Version '182'. The duplicates have been removed.|Test Tool|
    |Listing contains duplicate Accessibility Standard: '170.204(a)(1)'. The duplicates have been removed.|Accessibility Standard|
    |Listing contains duplicate QMS Standard: 'Home Grown, Criteria: All criteria tested'. The duplicates have been removed.|QMS Standard|
    |Listing contains duplicate Targeted User: 'Mental Health'. The duplicates have been removed.|Targeted User|
    |Listing contains duplicate Testing Lab: 'SLI Compliance'. The duplicates have been removed.|Testing Lab|
    |You have exceeded the max length, 300 characters, for the Targeted User Test for targeted users bad data greater than 300 chars - Order specific medication and then change the order based on the information provided. Test for targeted users bad data greater than 300 chars - Order specific medication and then change the order based on the information provided . Order specific medication and then change the order based on the information provided, which has been deleted.|Targeted User| 
  
  Scenario Outline: Verify warnings for duplicate input values in applicable fields on upload-inspect for 2014 listings
    Given I'm logged in as "ROLE_ACB"
    And I am on Upload Listings page
    When I upload a "2014" listing with CHPL ID "14.05.05.1447.SL14.v1.00.1.180707"
    Then I see upload successful message
    When I go to Confirm Pending Products Page
    And I open inspect form to inspect listing details
    Then I see warning: "<warning>" for duplicate value in "<field>"
  Examples:
    |warning|field|
    |Certification 170.314 (a)(1) contains duplicate Additional Software: CP Source 'CHP-022218'. The duplicates have been removed.|Additional Software|
    |Certification 170.314 (a)(1) contains duplicate Test Data: Version '1'. The duplicates have been removed.|Test Data|
    |Certification 170.314 (a)(1) contains duplicate Test Procedure: Version '1'. The duplicates have been removed.|Test Procedure|
    |Certification 170.314 (a)(1) contains duplicate Test Standard: Number 'Edge Protocols'. The duplicates have been removed.|Test Standard|
    |Certification 170.314 (a)(4) contains duplicate Test Functionality: Number '(a)(4)(iii)'. The duplicates have been removed.|Test Functionality|
    |Certification 170.314 (b)(1) contains duplicate Test Tool: Name 'Transport Testing Tool', Version '181'. The duplicates have been removed.|Test Tool|
    |Listing contains duplicate QMS Standard: 'ISO 9001. The duplicates have been removed.|QMS Standard|
    |Listing contains duplicate Testing Lab: 'SLI Compliance'. The duplicates have been removed.|Testing Lab|
    
  Scenario Outline: Verify listing upload fails for files that have invalid inputs in specific fields    
    Given I'm logged in as "ROLE_ACB"
    And I am on Upload Listings page
    When I upload a 2015 listing with invalid input in "<Field>" 
    Then I see upload failure with appropriate error message "<FailureError>" to indicate failure due to invalid data
  Examples:
    |Field|FailureError|
    |Test Task ID|You have exceeded the max length, 20 characters, for the Task Identifier with ID TestTaskIdisinvalidbecauseit'stoolong010101010101.|
    |Participant ID|You have exceeded the max length, 20 characters, for the Participant Identifier ID ParticipantIdisinvalidbecauseit'stoolong01010101010101.|
    |Certification date|Certification date <Date> is not in the format yyyymmdd.|
 
 Scenario Outline: Verify errors for bad input values in Test Task fields on upload-inspect for 2015 listings
    Given I'm logged in as "ROLE_ACB"
    And I am on Upload Listings page
    When I upload a 2015 listing with CHPL ID "15.05.05.1447.BDIN.v1.00.1.180707" that has bad input in "<field>"
    Then I see upload successful message
    When I go to Confirm Pending Products Page
    And I inspect listing details for listing with CHPL ID "15.05.05.1447.BDIN.v1.00.1.180707"
    Then I see error: "<error>" for bad value in "<field>"
    Examples:
    |error|field|
    |An unrecognized character was found in Test Participant "ID06" "Professional Experience Months" "88�". The value must be only a numeric value. You can correct it within the field itself on the Edit Certified Product screen or modify it in the csv file and upload again.|Professional Experience Months|
    |An unrecognized character was found in Test Task "A1.3" "Task Errors Standard Deviation" "9.75�". The value must be only a numeric value. You can correct it within the field itself on the Edit Certified Product screen or modify it in the csv file and upload again.|Task Errors Standard Deviation|
    |An unrecognized character was found in Test Task "A1.3" "Task Rating Standard Deviation" "1.00�". The value must be only a numeric value. You can correct it within the field itself on the Edit Certified Product screen or modify it in the csv file and upload again.|Task Rating Standard Deviation|
    |An unrecognized character was found in Test Task "A1.3" "Task Errors" "9.75�". The value must be only a numeric value. You can correct it within the field itself on the Edit Certified Product screen or modify it in the csv file and upload again.|Task Errors value|
    |Test Tool 'CDC's NHSN CDA Validator' can not be used for criteria '170.315 (b)(1)', as it is a retired tool, and this Certified Product does not carry ICS.|Test Tool|
    |You have exceeded the max length, 255 characters, for the QMS Standard|QMS Standard|    
    
    Scenario Outline: Verify warnings for bad input values in Test task and Participant fields on upload-inspect for 2015 listings
    Given I'm logged in as "ROLE_ACB"
    And I am on Upload Listings page
    When I upload a 2015 listing with CHPL ID "15.05.05.1447.BDIN.v1.00.1.180707" that has bad input in "<field>"
    Then I see upload successful message
    When I go to Confirm Pending Products Page
    And I inspect listing details for listing with CHPL ID "15.05.05.1447.BDIN.v1.00.1.180707"
    Then I see warning "<warning>" for non-integer numeric number in "<field>"
    Examples:
    |warning|field|
    |A non-integer numeric number was found in Test Participant "ID01" "Computer Experience Months" "250.75". The number has been rounded to "251".|Computer Experience Months|
    |A non-integer numeric number was found in Test Task "A1.1" "Task Path Deviation Observed" "9.75". The number has been rounded to "10".|Task Path Deviation Observed|
    |A non-integer numeric number was found in Test Task "A1.1" "Task Time Average" "90.2". The number has been rounded to "90".|Task Time Average|
    