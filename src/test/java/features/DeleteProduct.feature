Feature: Delete products using DELETE API
  Scenario Outline: validate delete product api works correctly
    Given I hit the url of delete product api endpoint
    When I pass the url of delete products in the request with <ProductNumber>
    Then I receive the response code as 200
    Examples:
      | ProductNumber |
      | 10            |

