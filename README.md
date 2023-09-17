# TPBackEndJPA
## Como ejecutar el programa

Para poder ejecutar el programa debemos seguir los siguientes pasos:
1. Clonar o descargar el repositorio.
   ```
   $ git clone https://github.com/expositoleo/TPBackEndJPA
   ```
2. Una vez descargado, debemos abrir el proyecto en un entorno de desarrollo JAVA.
3. Teniendo el proyecto, debemos abrir el archivo a ejecutar llamado `Ejercicio1Application.java`, ubicado en la siguiente ruta:
   `ejercicio1\src\main\java\com\utn\ejercicio1\Ejercicio1Application.java`
5. Una vez abierto el archivo ejecutaremos el programa desde nuestro entorno de desarrollo.
6. Por consola podremos ver las clases que van siendo recuperadas y mostradas.

## Interactuar con la base de datos en H2
1. Para poder ver los datos creados en la base, iremos a un navegador web y accederemos al siguiente link:
   <http://localhost:8080/h2-console/login.jsp>
   * Es importante dejar andando el programa `Ejercicio1Application.java` para que nuestra base siga funcionando.
2. En JDBC URL, asegurarse que esté escrito: `jdbc:h2:mem:./admin`
3. Hacer click en "connect".
4. Una vez adentro podremos ver una ventana blanca para introducir comandos SQL. Allí podremos visualizar todas las tablas mediante consultas. A continuación, algunos ejemplos:
  ```SQL
  // Junta todos los detalle pedido y su producto asociado
  SELECT dp.*, p.*
  FROM DETALLE_PEDIDO dp
  INNER JOIN PRODUCTO p ON dp.producto_id = p.id;
  ```
  ```SQL
  // Junta todos los domicilios y pedidos para cada cliente
  SELECT c.*, d.*, p.*
  FROM CLIENTE c
  LEFT JOIN DOMICILIO d ON c.id = d.cliente_id
  LEFT JOIN PEDIDO p ON c.id = p.cliente_id;
  ```
  ```SQL
  // Junta todos los detalles de pedido y la factura para cada pedido
  SELECT p.*, f.*, dp.*
  FROM PEDIDO p
  LEFT JOIN FACTURA f ON p.factura_id = f.id
  LEFT JOIN DETALLE_PEDIDO dp ON p.id = dp.pedido_id;
  ```
