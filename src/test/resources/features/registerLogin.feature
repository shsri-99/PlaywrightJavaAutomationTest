Feature: Register and Login

  Background:
    Given User is on the homepage
    And User navigates to the sign-in page
    And User clicks on the register link
    And User is on the customer registration page

  Scenario: Navigate to Register using the UI
    Then User is on the customer registration page

  Scenario: Receive alert message on submitting Register without any information
    When User submits Register button
    Then User receives alert message

  Scenario: Register user via API and receive success response
    Given I set the request URL to "https://api.practicesoftwaretesting.com/users/register"
    And I set the request body to:
    """
    {
      "first_name": "Johny",
      "last_name": "Does",
      "address": {
        "street": "Street 1",
        "city": "City",
        "state": "State",
        "country": "Country",
        "postal_code": "1234AA"
      },
      "phone": "0987352332",
      "dob": "1998-01-01",
      "password": "SuperSecure@123",
      "email": "Johny@Does.example"
    }
    """
    When I send a POST request
    Then the response status code should be 201
    And the response body should contain:
    """
    {
  "first_name": "Johny",
  "last_name": "Does",
  "phone": "0987352332",
  "dob": "1998-01-01",
  "email": "Johny@Does.example",
  "id": "01jw10et38ps943kpr3j5p1ff5",
  "created_at": "2025-05-24 11:22:09",
  "address": {
    "street": "Street 1",
    "city": "City",
    "state": "State",
    "country": "Country",
    "postal_code": "1234AA"
  }
}
    """

Scenario: Login using the newly registered credentials through API
  Given User navigates to the sign-in page
  When User enters "john@doe.example" email address
  And User enters "SuperSecure@123" password
  And User submits login
  Then "My account" is visible in the page

Scenario: User selects login without adding email password gives error message
  Given User navigates to the sign-in page
  When User submits login
  Then User receives "Email is required" and "Password is required" message