Feature: Create customer API Test

  @positive @alldata
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

#  @negative
#  Scenario Outline: Check validation on customer creation without mandatory fields
#    Given I have create customer API URL <URL>
#    And I have customer name <CUSTOMER_NAME>, customer address <ADDRESS>, onboard status <ONBOARD_STATUS>, status <STATUS>, view id <VIEW_ID> and workflow id <WORKFLOW_ID>
#    When I send POST request with customer data
#    Then I should receive <HTTP_STATUS> as HTTP status code
#    And I should receive error message <ERROR>
#
#    Examples: Customer data
#      |URL|CUSTOMER_NAME|ADDRESS|ONBOARD_STATUS|STATUS|VIEW_ID|WORKFLOW_ID|ERROR|

#  @negative
#  Scenario: Validate error on creating customer with wrong workflow id
#    Given I have create customer API URL <URL>
#    And I have customer name <CUSTOMER_NAME>, customer address <ADDRESS>, onboard status <ONBOARD_STATUS>, status <STATUS>, view id <VIEW_ID> and workflow id <WORKFLOW_ID>
#    When I send POST request with customer data
#    Then I should receive <HTTP_STATUS> as HTTP status code
#    And I should receive error message <ERROR>
#
#  @negative
#  Scenario: Validate error on creating customer with blank name
#    Given I have create customer API URL <URL>
#    And I have customer name <CUSTOMER_NAME>, customer address <ADDRESS>, onboard status <ONBOARD_STATUS>, status <STATUS>, view id <VIEW_ID> and workflow id <WORKFLOW_ID>
#    When I send POST request with customer data
#    Then I should receive <HTTP_STATUS> as HTTP status code
#    And I should receive error message <ERROR>
#
#  @negative
#  Scenario: Validate error on creating customer with blank address
#    Given I have create customer API URL <URL>
#    And I have customer name <CUSTOMER_NAME>, customer address <ADDRESS>, onboard status <ONBOARD_STATUS>, status <STATUS>, view id <VIEW_ID> and workflow id <WORKFLOW_ID>
#    When I send POST request with customer data
#    Then I should receive <HTTP_STATUS> as HTTP status code
#    And I should receive error message <ERROR>
#
#  @negative
#  Scenario: Validate error on creating customer with blank address
#    Given I have create customer API URL <URL>
#    And I have customer name <CUSTOMER_NAME>, customer address <ADDRESS>, onboard status <ONBOARD_STATUS>, status <STATUS>, view id <VIEW_ID> and workflow id <WORKFLOW_ID>
#    When I send POST request with customer data
#    Then I should receive <HTTP_STATUS> as HTTP status code
#    And I should receive error message <ERROR>

#  @negative
#  Scenario Outline: Check validation on creation with invalid optional fields
#    Given I have create customer API URL <URL>
#    And I have customer name <CUSTOMER_NAME>, customer address <ADDRESS>, onboard status <ONBOARD_STATUS>, status <STATUS>, view id <VIEW_ID> and workflow id <WORKFLOW_ID>
#    When I send POST request with customer data
#    Then I should receive <HTTP_STATUS> as HTTP status code
#    And I should receive error message <ERROR>

  @negative
  Scenario: Create a customer with blank view id
    Given The app database has dummy customer data
    And The client has customer data User 4,Chennai,true,BLOCKED,2 but no view Id
    When The client send POST using API /customer
    Then It should receive 404 as HTTP status code
    And customer database should not be updated

  @negative
  Scenario: Create a customer with blank workflow id
    Given The app database has dummy customer data
    And The client has customer data User 5,Delhi,false,ACTIVE,4 but no workflow Id
    When The client send POST using API /customer
    Then It should receive 404 as HTTP status code
    And customer database should not be updated