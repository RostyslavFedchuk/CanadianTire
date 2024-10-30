Feature: Canadian Tire Product search

  Scenario Outline: Verify product availability on Global Search
    Given I am on the Canadian Tire website
    When I search for a product
      | searchKeyword   | name   | width   | aspectRatio   | diameter   | tireCount   |
      | <searchKeyword> | <name> | <width> | <aspectRatio> | <diameter> | <tireCount> |
    Then I should see the product availability of "<tireCount>" tires

    Examples:
      | searchKeyword  | name                                                            | width | aspectRatio | diameter | tireCount |
      | michelin tires | Michelin CrossClimate® 2 All Weather Tire For Passenger and CUV | 245   | 50          | 19       | 4         |
