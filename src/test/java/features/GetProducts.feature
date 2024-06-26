Feature: Get all products from the API
  Scenario: Verify the get api for the products
    Given I hit the url of get products api endpoint
    When I pass the url in the request
    Then I receive the response code as 200

  Scenario: verify the get api for the products, invalid endpoint
    Given I hit the url of get products api endpoint
    When I pass the incorrect url in the request
    Then I receive the invalid response code as 404

  Scenario Outline:  verify the rate of the first product is correct
    Given I hit the url of get products api endpoint
    When I pass the url in the request
    Then I verify that the rate of the first product is <FirstProductRate>
    Examples:
          |FirstProductRate|
          |3.9         |