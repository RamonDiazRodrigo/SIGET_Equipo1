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
  Scenario: Registro correcto
    Given "PruebaRegistro","registro@registro.com","Password1", "Password1" y "ASISTENTE"
    Then Crea un usuario "PruebaRegistro"
    
  @tag2
  Scenario: Registro con password distintas
    Given "PruebaRegistro","registro@registro.com","Password1", "Password2" y "ASISTENTE" distintas
    When "Password1" y "Password2" son distintas
    Then se lanza la excepcion DiferentesContrasenas "PruebaRegistro"
    
  @tag3
  Scenario: Registro usuario ya existente
    Given "PruebaRegistro","registro@registro.com","Password1", "Password1" y "ASISTENTE" distintas
    When ya existe "PruebaRegistro" 
    Then se lanza la excepcion usuario ya existe "PruebaRegistro" 
