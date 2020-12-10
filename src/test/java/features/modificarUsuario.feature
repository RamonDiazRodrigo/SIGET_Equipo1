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
Feature: Modificar usuario

 @tag1
  Scenario: Modificar email usuario
    Given Como admin en la vista del user "Alvaro","alvaro@gmail.com","Alvaro1"
    When Como admin el user"Alvaro"con contraseniaa "Alvaro1" ha sido modificado "alvarito@gmail.com"
		Then Como admin el user"Alvaro"ha sido modificado el correo "alvarito@gmail.com" correctamente
		
	@tag2
  Scenario: Modificar password usuario
    Given Como admin en la vista del user "Alvaro","alvaro@gmail.com","Alvaro1"
    When Como admin el user "Alvaro" con el correo "alvaro@gmail.com"  ha sido modificado "Contraseña1"
		Then Como admin el user "Alvaro" ha sido modificado la contraseniaa "Contraseña1" correctamente