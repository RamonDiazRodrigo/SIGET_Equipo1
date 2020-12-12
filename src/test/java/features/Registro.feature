Feature: Registrarse en la aplicación como asistente

  Scenario Outline: Se registra en la aplicacion
    Given registro
    When introducir los datos de registro
      | nombre   | email   | password   | passwordconfirm   |
      | <nombre> | <email> | <password> | <passwordconfirm> |
    Then pulsamos REGISTRARSE

    Examples: 
      | testCase | resultado               | nombre           | email               | password    | passwordconfirm |
      | Case 1   | Registro Correcto       | PruebaTestClient | prueba@registro.com | Contraseña1 | Contraseña1     |
      | Case 2   | Usuario ya existente    | Daniel           | prueba@registro.com | Contraseña1 | Contraseña1     |
      | Case 3   | No password Introducida | PruebaTestClient | prueba@registro.com |             | Contraseña1     |
      | Case 4   | No e-mail Introducido   | PruebaTestClient |                     | Contraseña1 | Contraseña1     |
      | Case 5   | contraseñas distintas   | PruebaTestClient | prueba@registro.com | Contraseña1 | Contraseña2     |
