Feature: As a guest user, I want to be able to add an item to cart and checkout without logging into account

  Scenario: As a guest user, I want to be able to add an item to cart and verify the price are equal in all stages

    Given  User sends text in the "MainPage"
      | Search Field | qa testing for beginners |

    When User clicks in the "MainPage"
      | Search Button |

    Then User verifies an item from the list in the "MainPage" has the price
   #  | Element Name        | Which Element By Index|
      | Search output list  |         0             |

    When In the "MainPage" user clicks on the item from the list by index
   #  | Element Name        | Which Element By Index|
      | Search output list  |         0             |

    Then User verifies price in the "OrderPage"
      |Buy new price|

    When User clicks in the "OrderPage"
      | Add to Cart|
      | Proceed to checkout|

    And  User sends text in the "CheckoutPage"
      | Email Input Field | fortestwebsite2021@gmail.com |

    And User clicks in the "CheckoutPage"
      | Continue |

    And  User sends text in the "CheckoutPage"
      |Password Input Field| test123456789$ |

    And User clicks in the "CheckoutPage"
      | Sign-In |

    And User clicks in the "CheckoutPage"
      | Deliver to this address |

    Then User verifies price in the "CheckoutPage"
      |Checkout Price|



