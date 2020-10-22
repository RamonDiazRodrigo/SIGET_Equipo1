#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and "placeholder"
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#"" (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Registrarse

  @tag1
  Scenario Outline: Registro correcto
    Given Registro con "nombre","email","password" y "rol"
    Then Crea un usuario 

	@tag2
  Scenario Outline: Registro con nombre incorrecto
    Given Registro con "nombre" y "password"
    When el "nombre" esta vacio y "password" bien
    Then Da un error de registro
    
  @tag3
  Scenario Outline: Registro con password incorrecto
    Given Registro con "nombre" y "password"
    When el "nombre" esta bien y "password" vacio
    Then Da un error de registro
