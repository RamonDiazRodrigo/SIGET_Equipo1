
Feature: Registrarse

   Given registro
    When introducir los datos de registro
      | nombre   | email   | password   | passwordconfirm   |
      | <nombre> | <email> | <password> | <passwordconfirm> |
    Then le damos a registrar

    Examples: 
      | testCase | resultado               | nombre       | email     | password  | passwordconfirm    | 
      | Case 1   | Registro Correcto       | PruebaTestClient | prueba@registro.com   | Contraseña1   | Contraseña1  | 
      | Case 2   | Usuario ya existente    | PruebaTestClient | prueba@registro.com   | Contraseña1   | Contraseña1  |
      | Case 3   | No password Introducida | PruebaTestClient | prueba@registro.com   |    | Contraseña1  |
      | Case 4   | No e-mail Introducido   | PruebaTestClient | prueba@registro.com   | Contraseña1   | Contraseña1  |
      | Case 5   | No telefono Introducido  |PruebaTestClient | prueba@registro.com   | Contraseña1   | Contraseña1  |

      