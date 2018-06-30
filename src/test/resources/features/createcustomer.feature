Feature: Create customer API Test

  @positive
  Scenario: Create a customer with valid details
    Given The app database has dummy customer data
    And The client has customer data User 5,Kolkata,false,INACTIVE,1,1
    When The client send POST using API /customer
    Then It should receive 200 as HTTP status code
    And customer database should be updated

  @positive
  Scenario: Create a customer with blank onboard status
    Given The app database has dummy customer data
    And The client has customer data User 6,Bangalore,ACTIVE,2,1 but no onboard status
    When The client send POST using API /customer
    Then It should receive 200 as HTTP status code
    And customer database should be updated

  @positive
  Scenario: Create a customer with blank status
    Given The app database has dummy customer data
    And The client has customer data User 7,Mumbai,true,3,2 but no status
    When The client send POST using API /customer
    Then It should receive 200 as HTTP status code
    And customer database should be updated

  @positive
  Scenario: Create a customer with special characters
    Given The app database has dummy customer data
    And The client has customer data `~!@#$%^&*()_-+=|\\}]{[\"':;?/>.<,,`~!@#$%^&*()_-+=|\\}]{[\"':;?/>.<,,true,ACTIVE,2,2
    When The client send POST using API /customer
    Then It should receive 200 as HTTP status code
    And customer database should be updated

  @negative
  Scenario: Check validation on customer creation without mandatory name
    Given The app database has dummy customer data
    And The client has customer data Pune,false,INACTIVE,1,1 without name
    When The client send POST using API /customer
    Then It should receive 400 as HTTP status code
    And customer database should not be updated

  @negative
  Scenario: Check validation on customer creation without mandatory address
    Given The app database has dummy customer data
    And The client has customer data User 10,true,ACTIVE,3,4 without address
    When The client send POST using API /customer
    Then It should receive 400 as HTTP status code
    And customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with blank view id
    Given The app database has dummy customer data
    And The client has customer data User 4,Chennai,true,BLOCKED,2 but no view id
    When The client send POST using API /customer
    Then It should receive 404 as HTTP status code
    And customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with blank workflow id
    Given The app database has dummy customer data
    And The client has customer data User 5,Delhi,false,ACTIVE,4 but no workflow id
    When The client send POST using API /customer
    Then It should receive 404 as HTTP status code
    And customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with invalid view
    Given The app database has dummy customer data
    And Client has customer data User 11,Jaypur,true,INACTIVE,-7,4 with invalid view
    When The client send POST using API /customer
    Then It should receive 404 as HTTP status code
    And Client should get error message Specified view does not exist
    And customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with invalid workflow
    Given The app database has dummy customer data
    And Client has customer data User 12,Goa,true,ACTIVE,3,-9 with invalid workflow
    When The client send POST using API /customer
    Then It should receive 404 as HTTP status code
    And Client should get error message Specified workflow does not exist
    And customer database should not be updated

    @negative
    Scenario: Check validation on customer creation with invalid request format containing id
      Given The Client has customer data 1, User 13,Ahmedabad,true,ACTIVE,2,1 with invalid workflow
      When The client send POST using API /customer
      Then It should receive 400 as HTTP status code
      And Client should get error message The request could not be understood by the server due to malformed syntax
      And customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with invalid request URL
    Given The client has customer data User 14,Noida,true,ACTIVE,2,1
    When The client send POST using API /customer/abc
    Then It should receive 405 as HTTP status code
    And Client should get error message Method Not Supported

  @negative
  Scenario: Check validation on customer creation with invalid request URL
    Given The client has customer data User 13,Gurgaon,true,ACTIVE,2,1
    When The client send POST using API /custom
    Then It should receive 404 as HTTP status code