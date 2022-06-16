# VaccinationKrugerBack

Para ejecutar el proyecto priorizar lo siguiente 
Ir al directorio documents y ejecutar el script database.txt 
1. Crear la base de datos.
2. Crear el nuevo usuario rol para la conexion con la base de datos

# Desplegar el servicio back end 

Ejecutar los pasos 1, 2 y 3 a continuación  solo si al momento de desplegar el aplicativo no ejecutó las sentencias sql necesarias en la base.

1. Inserta los roles directamente con los scripts
2. Insertar el usuario admin de prueba
3. Ingresar el rol con el usuario

# Notas:

* El aplicativo tiene un usuario de tipo ADMINISTRADOR
      username = 0930007539
      password = 12345

* Los usuarios de tipo empleado solo se crean atraves de la aplicacion realizando la peticiones a la API

* Los credenciales de los mismo se devuelven en formato json en los campos:
username y password
* Modelado de la base de datos adjunto en la carpeta documents