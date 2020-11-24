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
  Scenario: Acceder a la aplicacion satisfactoriamente Asistente
    Given acceso con "nombre" y "password" correctos asistente
    When los datos son correctos y de un asistente
    Then Accedo a la pantalla principal de asistente
  
  @tag2
  Scenario: Acceder a la aplicacion satisfactoriamente Admin
    Given acceso con "nombre" y "password" correctos admin
    When los datos son correctos y de un administrador
    Then Accedo a la pantalla principal de admin
  
  @tag3
  Scenario: Acceder a la aplicacion con la contrasena mal puesta
    Given acceso con "nombre" y "password"
    When intento acceder con el "nombre" correcto y "password" mal
    Then se lanza la excepcion CredencialesInvalidas

    
