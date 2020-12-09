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
    Given los datos"PruebaRegistro1","registro1@registro.com","Password1", "Password1", "ASISTENTE"
    Then el usuario se ha creado correctamente "PruebaRegistro1" 
    
  @tag2
  Scenario: Registro usuario ya existente
    Given el usuario "PruebaRegistro3","registro3@registro.com","Password1", "Password1", "ASISTENTE"
    When ya existe "PruebaRegistro3" 
    Then se lanza la excepcion ya existe "PruebaRegistro3"
