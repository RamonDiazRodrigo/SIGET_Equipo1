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
    Given Escribo las credenciales que quiero
    When Valido la operacion "nombre" y "password"
    Then Crea un usuario

	@tag2
  Scenario: Registro con nombre incorrecto
    Given Escribo las credenciales que quiero
    When Valido la operacion con "nombre" vacio y "password" bien
    Then Da un error
    
  @tag3
  Scenario: Registro con password incorrecto
    Given Escribo las credenciales que quiero
    When Valido la operacion con "nombre" bien y "password" incorrecto
    Then Da un error
