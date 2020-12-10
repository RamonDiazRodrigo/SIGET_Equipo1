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
Feature: Introducir actividades no laborables

  @tag1
  Scenario: Introduccion valida de actividad no laborable
    Given "Descanso", "SABADO",  "10",  "00",  "11",  "30", "Usuario" involucrado y si es "false" 
    Then se aniade la actividad no laborable con "Descanso", "SABADO", "10",  "00",  "11",  "30", "false" y se vincula al "Usuario"

  @tag2
  Scenario: Introduccion invalida de actividad no laborable usuario no existe
    Given "Descanso", "MARTES",  "10",  "00",  "11",  "30", "FEDERICOOO" involucrado y si es "false" 
    When usuario no esta registrado
    Then no se aniade la actividad no laborable con "Descanso", "MARTES", "10",  "00",  "11",  "30", "false" y se vincula al "FEDERICOO"
    
	@tag3
  Scenario: Introduccion invalida de actividad no laborable dia no existe
    Given "Descanso", "CAMA",  "10",  "00",  "11",  "30", "Usuario" involucrado y si es "false" 
    When el dia no es correcto
    Then no se aniade la actividad no laborable con "Descanso", "CAMA", "10",  "00",  "11",  "30", "false" y se vincula al "Usuario"
    
	@tag4
  Scenario: Introduccion invalida de actividad no laborable hora inicial no existe
    Given "Descanso", "LUNES",  "30",  "00",  "11",  "30", "Usuario" involucrado y si es "false" 
    When la hora inicial no es correcta
    Then no se aniade la actividad no laborable con "Descanso", "LUNES", "30",  "00",  "11",  "30", "false" y se vincula al "Usuario"
    
   @tag5
  Scenario: Introduccion invalida de actividad no laborable hora final no existe
    Given "Descanso", "LUNES",  "30",  "00",  "30",  "30", "Usuario" involucrado y si es "false" 
    When la hora final no es correcta
    Then no se aniade la actividad no laborable con "Descanso", "LUNES", "10",  "00",  "30",  "30", "false" y se vincula al "Usuario"