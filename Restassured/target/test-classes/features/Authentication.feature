Feature:
  Verify different GET operations using REST-assured
  
  
Scenario: Verify GET operation with bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
      | email              | password  |
      | shrey@email.com | 123 |
    Given I Perform GET operation for "/posts/1"
    Then I should see the author Name as "Shrey"