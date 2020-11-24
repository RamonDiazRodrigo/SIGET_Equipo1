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
Feature: Asistir Reuniones

  @tag1
  Scenario: Aceptar reunion
		Given "nombre reunion", "dia de la semana",  "hora de inicio",  "minutos de inicio",  "hora de fin",  "minutos de fin" y un "usuario"   
  	Then el "usuario" acepta la reunion "id" y aparece en su agenda
  
  @tag1
  Scenario: Rechazar reunion
		Given "nombre reunion", "dia de la semana",  "hora de inicio",  "minutos de inicio",  "hora de fin",  "minutos de fin" y un "usuario"   
  	Then el "usuario" acepta la reunion "id" y no aparece en su agenda
  	