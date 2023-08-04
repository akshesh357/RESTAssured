Feature:
  Verify different GET operations using REST-assured

 
  Scenario: Verify one author of the post
    Given I Perform GET operation for "/posts"
    And I perform GET for the post number "1"
    Then I should see the author Name as "typicode"


  Scenario: Verify Path parameter in the post
    Given I Perform GET operation for "/post"
    Then I perform Path parameter


  Scenario: Verify Query parameter in the post
    Given I Perform GET operation for "/post"
    Then I perform Query parameter
    
 Scenario: Verify POST operation
    Given I Perform POST operation for "/post"
    
Scenario: Verify Post operation for Profile
    Given I Perform POST operation for "/posts/{profileNo}/profile" with body
      | name | profile |
      | Sams | 9       |
    Then I should see the body has name as "Sams"
    
Scenario: Verify DELETE operation after POST
    Given I ensure to Perform POST operation for "/posts" with body as
      | id | title              | author            |
      | 6  | API Testing course | ExecuteAutomation |
    And  I Perform DELETE operation for "/posts/{postid}/"
      | postid |
      | 6      |
    And I perform GET operation with path parameter for "/posts/{postid}"
      | postid |
      | 6      |
    Then I should not see the body with title as "API Testing course"
    
    
Scenario: Verify PUT operation after POST
    Given I ensure to Perform POST operation for "/posts" with body as
      | id | title              | author            |
      | 11  | API Testing course | ExecuteAutomation |
    And  I Perform PUT operation for "/posts/{postid}/"
      | id | title             | author            |
      | 6  | API Testing Guide | ExecuteAutomation |
    And I perform GET operation with path parameter for "/posts/{postid}"
      | postid |
      | 11      |
    Then I should see the body with title as "API Testing Guide"
    
    

 


