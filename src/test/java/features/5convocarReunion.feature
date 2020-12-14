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
Feature: Convocar Reuniones

  @tag1
  Scenario: Convocar reunion
    Given los datos "PruebaConvocar1", "LUNES",  "12",  "00",  "13",  "00", "2020-W52" y "PruebaRegistro1" involucrados
    Then Crea una reunion "PruebaConvocar1", "LUNES",  "12",  "00",  "13",  "00", "2020-W52" y "PruebaRegistro1" y la aniade a reuniones pendientes si no tienen ese horario ocupado
    
   @tag2
  Scenario: Convocar reunion ya existente
    Given los datos "PruebaConvocar1", "LUNES",  "12",  "00",  "13",  "00", "2020-W52", "PruebaRegistro1"
    When ya existe "PruebaConvocar1" 
    Then se lanza la excepcion ya existe reunion "PruebaConvocar1", "LUNES",  "12",  "00",  "13",  "00", "2020-W52", "PruebaRegistro1"
    
    @tag3
  Scenario: Convocar reunion dia mal
    Given la reunion "PruebaConvocar3", "JUERNES",  "12",  "00",  "13",  "00", "2020-W52", "PruebaRegistro1"
    When  el dia no existe
    Then se lanza la excepcion dia no existe para la reunion "PruebaConvocar3", "LUNES",  "12",  "00",  "13",  "00", "2020-W52", "PruebaRegistro1"
    
    @tag4
    Scenario: Convocar reunion hora inicial no existe
    Given la reunion "PruebaConvocar4", "LUNES",  "54",  "70",  "13",  "00", "PruebaRegistro1" con la hora incial mal
    When  la hora inicial no existe
    Then se lanza la excepcion hora inicial no existe para la reunion "PruebaConvocar4", "LUNES",  "12",  "00",  "13",  "00" "PruebaRegistro1"
    
    @tag5
    Scenario: Convocar reunion hora Final no existe
    Given la reunion "PruebaConvocar5", "LUNES",  "12",  "00",  "12",  "89", "PruebaRegistro1" con la hora final mal
    When  la hora final no existe
    Then se lanza la excepcion hora final no existe para la reunion "PruebaConvocar5", "LUNES",  "12",  "00",  "13",  "00" "PruebaRegistro1"
    
