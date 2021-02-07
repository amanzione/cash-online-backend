# cash-online-backend
Backend para el challenge de cash-online. Proyecto utilizando springboot con maven desarrollado usando el flujo de TDD (Red - Green - Refactor).

Para iniciar la aplicación:

Clonar el proyecto de git y pararse en la carpeta base. Desde la consola ejecutar el comando
    mvnw spring-boot:run


Se proveen datos de prueba para los siguientes usarios que cargan al iniciar la app
-1001
-1002
-1003 (no loans)
-1004

Dado que es un proyecto demo se utilizó H2 usando almacenamiento en memoria por lo tanto al reiniciarla se borran todos los datos agregados manualmente.

Posibles mejoras / Que seguiría:
-Agregar documentación de las api siguiendo la especificación de openapi http://spec.openapis.org/oas/v3.0.3
-Mejorar logging para obtener datos de valor
-Agregar Auth a los requests
-Sprinboot Actuator
-Se aceptan sugerencias!
