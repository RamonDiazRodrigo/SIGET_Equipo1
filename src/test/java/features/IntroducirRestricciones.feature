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
Feature: Introducir restricciones

  @tag1
  Scenario: Introduccion valida de restriccion
    Given "franja temporal", "nombre periodo no laborable" y "usuario" involucrado
    Then se añade la restriccion horaria con "franja temporal", "nombre periodo no laborable" y "usuario"

  @tag2
  Scenario: Introduccion invalida de restriccion usuario no existe
    Given "franja temporal", "nombre periodo no laborable" y "nombre usuario" involucrado
    When usuario no esta registrado
    Then se lanza excepcion usuario no existe
    

    
