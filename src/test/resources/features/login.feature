#Autor: Luis González
  #language: es

@Regresion
Característica: : Login Sigpos

  @CP01
  Esquema del escenario: : Usuario tratando de iniciar sesión con credenciales válidas
    Dado El usuario está en la página de inicio de sesión
    Cuando El usuario ingresa credenciales válidas
    |usuario|clave|
    |<usuario>|<clave>|
    Entonces El usuario debería ser redirigido a la página de inicio

    Ejemplos:
      |usuario       |clave     |
      ##@externaldata@C:\\Insumos\\datosSigposLoombokData.xlsx@CP01
      |lgonzalez|lgonzalez|
