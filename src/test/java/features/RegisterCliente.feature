#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Registro cliente

  @tag1
  Scenario Outline: Registro de un usuario
    Given Quiero registrar un usuario
    When introduzco los valores 
      | testCase | resultado                           | nombre           | email               | password    | passwordconfirm |
      | Case 1   | http://localhost:8080/index.html    | PruebaTestClient | prueba@registro.com | Contraseña1 | Contraseña1     |
      | Case 2   | http://localhost:8080/register.html | Daniel           | prueba@registro.com | Contraseña1 | Contraseña1     |
      | Case 3   | http://localhost:8080/register.html | PruebaTestClient | prueba@registro.com |             | Contraseña1     |
      | Case 4   | http://localhost:8080/register.html | PruebaTestClient |                     | Contraseña1 | Contraseña1     |
      | Case 5   | http://localhost:8080/register.html | PruebaTestClient | prueba@registro.com | Contraseña1 | Contraseña2     |
    Then le damos a registrar

    
