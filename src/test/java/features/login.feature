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
Feature: Login

  @tag1
  Scenario Outline: Acceder a la aplicacion con la contraseña mal puesta
    Given Pongo mis credenciales
    When intento acceder con el "nombre" correcto y "password" mal
    Then da error

  @tag2
  Scenario Outline: Acceder a la aplicacion con el usuario mal puesta
    Given Pongo mis credenciales
    When intento acceder con el "nombre" mal y "password" correcto
    Then da error
    
  @tag3
  Scenario Outline: Acceder a la aplicacion satisfactoriamente
    Given Pongo mis credenciales
    When intento acceder con el "nombre" y "contraeña"
    Then puedo ver mi calendario

