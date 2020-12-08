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
Feature: Modificar datos

	@tag1
  Scenario: Modificar email usuario
    Given En la vista user "Daniel","daniel@gmail.com","Daniel1"
    When El user "Daniel" con contraseña "Daniel1" ha modificado "danielito@gmail.com"
		Then El user "Daniel" ha modificado el correo "danielito@gmail.com" correctamente
		
	@tag2
  Scenario: Modificar password usuario
    Given En la vista user "Daniel","daniel@gmail.com","Daniel1"
    When El user "Daniel" con el correo "daniel@gmail.com"  ha modificado "Contraseña1"
		Then El user "Daniel" ha modificado la contraseña "Contraseña1" correctamente
	
	
