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
  Scenario: Modificar nombre usuario
    Given En la vista admin "nombre","email","password" y "rol"
    When "nombre", "nombreNuevo" son distintas
		Then Se ha modificado el usuario correctamente
		
	@tag2
  Scenario: Modificar email usuario
    Given En la vista admin "nombre","email","password" y "rol"
    When "email", "emailNuevo" son distintas
		Then Se ha modificado el usuario correctamente
		
	@tag3
  Scenario: Modificar password usuario
    Given En la vista admin "nombre","email","password" y "rol"
    When "password", "passwordNueva" son distintas
		Then Se ha modificado el usuario correctamente
	
	
	@tag4
  Scenario: Modificar rol usuario
    Given En la vista admin "nombre","email","password" y "rol"
    When "rol", "rolNuevo" son distintas
		Then Se ha modificado el usuario correctamente
	

