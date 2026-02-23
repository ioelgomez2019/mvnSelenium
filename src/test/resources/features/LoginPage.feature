@login
Feature: Funcionalidad de inicio de sesión en OpenCart

  Como usuario registrado
  Quiero iniciar sesión en OpenCart
  Para acceder a mi cuenta

  Background:
    Given que estoy en la página de inicio de sesión de OpenCart

  Scenario: Inicio de sesión exitoso con credenciales válidas
    Given que ingreso un usuario y contraseña válidos
    When hago clic en el botón Login
    Then debo iniciar sesión correctamente
    And debo ser redirigido al panel de mi cuenta

  Scenario Outline: Inicio de sesión fallido con credenciales inválidas o vacías
    Given que ingreso "<username>" y "<password>" inválidos
    When hago clic en el botón Login
    Then debo ver un mensaje de error que indique "<error_message>"

    Examples:
      | username           | password        | error_message                                      |
      | invalidsecret_sauce  | secret_sauce | Warning: No match for E-Mail Address and/or Password. |
      | abcccc             | validPassword   | Warning: No match for E-Mail Address and/or Password. |
      | standard_user   | secret_sauce          | Warning: No match for E-Mail Address and/or Password. |
      |                    |                 | Warning: No match for E-Mail Address and/or Password. |

  Scenario: Navegación a la página de contraseña olvidada
    When hago clic en el enlace "Forgotten Password"
    Then debo ser redirigido a la página de recuperación de contraseña