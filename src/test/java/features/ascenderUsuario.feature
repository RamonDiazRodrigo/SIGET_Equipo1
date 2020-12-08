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
Feature: Ascender usuario

  @tag1
  Scenario: Ascender usuario
    Given un usuario "nombre"
    When un administrador asciende al usuario "nombre"
    Then el usuario "nombre" ahora es administrador
  @tag2
  Scenario: Ascender usuario que ya es administrador
    Given un usuario "nombre"
    When un administrador asciende al usuario "nombre"
    And el usuario "nombre" ya es administrador
    Then el usuario "nombre" sigue siendo administrador