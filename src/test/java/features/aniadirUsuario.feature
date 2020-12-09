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
Feature: Aniadir usuario

 @tag1
  Scenario: Aniadir usuario
  Given En la vista admin "PruebaDani1","admin@admin.com","Password2", "Password2" y "ADMIN"
  Then el usuario se ha creado el usuario correctamente "PruebaDani1"

 @tag2
  Scenario: Aniadir usuario con rol no valido
  Given "PruebaDani2","admin@admin.com","Password1", "Password2" y "Rolmal" rol no valido
  When "Password1" y "Password2" son distintas
  Then se lanza la excepcion DiferentesContrasenas "PruebaDani2"
  
 @tag3
  Scenario: Aniadir usuario con password no valida
  Given "PruebaDani3","email@email.com","password", "password" y "ADMIN" no valida
  When "password" no cumple requisitos de contrasena
  Then se lanza la excepcion contrasenaNoValida "PruebaDani3"
  
 @tag4
  Scenario: Aniadir usuario con email no valido
  Given "PruebaDani4","emailemail.com","Password1", "Password1" y "ADMIN" email no valido
  When "emailemail.com"  no valido
  Then se lanza la excepcion emailNoValido "PruebaDani4"
