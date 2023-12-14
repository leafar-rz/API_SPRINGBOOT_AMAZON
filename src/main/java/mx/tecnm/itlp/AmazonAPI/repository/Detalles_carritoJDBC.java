package mx.tecnm.itlp.AmazonAPI.repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.itlp.AmazonAPI.models.ConsultaUnidad;

@Repository
public class Detalles_carritoJDBC {

    @Autowired
    JdbcTemplate conexion;

    String sql;

    // 1- Insertar productos al carrito de compras
    public void insertar(int cantidad, double precio, int Productos_id, int Usuario_id) {
        sql = "INSERT INTO DETALLES_CARRITO (cantidad,precio,Productos_id,Usuario_id) values (?,?,?,?);";
        conexion.update(sql, cantidad, precio, Productos_id, Usuario_id);
    }

    // 2- Cambiar la cantidad de productos del carrito
    public int update(int id, int cantidad) {
        sql = "UPDATE DETALLES_CARRITO SET cantidad = ? WHERE id = ?;";
        return conexion.update(sql, cantidad, id);
    }

    // 3- Quitar un producto del carrito
    public int delete(int id) {
        sql = "DELETE FROM DETALLES_CARRITO WHERE id=?;";
        return conexion.update(sql, id);
    }

    // 4- Agregar productos al carrito que ya existen en el carrito (si ya existen
    // que incremente la cantidad)
    public void insertarSinRepetir(int cantidad, double precio, int Productos_id, int Usuario_id) {
        System.out.println("HOLA: " + cantidad + "-" + "-" + precio + "-" + "-" + Productos_id + "-" + Usuario_id);
        sql = "SELECT COUNT(*) FROM DETALLES_CARRITO WHERE Productos_id=? AND Usuario_id=?;";
        int rowCount = conexion.queryForObject(sql, Integer.class, Productos_id, Usuario_id);

        System.out.println("Número de filas encontradas: " + rowCount);

        if (rowCount > 0) {
            System.out.println("Entre aquí 1");
            sql = "UPDATE DETALLES_CARRITO SET cantidad = cantidad + ? WHERE Productos_id = ? AND Usuario_id = ? limit 1;";
            conexion.update(sql, cantidad, Productos_id, Usuario_id);
        } else {
            System.out.println("Entre aquí 2");
            sql = "INSERT INTO DETALLES_CARRITO (cantidad, precio, Productos_id, Usuario_id) VALUES (?, ?, ?, ?);";
            conexion.update(sql, cantidad, precio, Productos_id, Usuario_id);
        }
    }

    /*
     * 5- Consultar el subtotal, cantidad de productos, nombre del usuario
     * y productos que se han agregado al carrito mostrando la descripcion, cantidad
     * y precio,
     * seleccionadolo por su id del usuario
     */

    
    public List<ConsultaUnidad> consultar(int id) throws Exception {
        String sql = "SELECT U.subtotal, U.cantidad_productos, U.nombre, P.nombre, DC.cantidad, P.precio "
                +
                "FROM PRODUCTOS P " +
                "JOIN DETALLES_CARRITO DC ON (P.id=DC.Productos_id) " +
                "JOIN USUARIO U ON (U.id=DC.Usuario_id) " +
                "WHERE U.id=?;";

        List<ConsultaUnidad> consultaResultados = conexion.query(sql, new ConsultaUnidadRM(), id);

        System.out.println("HOLA 1");

        return consultaResultados;
    }

    public Map<String, Object> consultar2(int id) throws Exception {
        //info del usuario
        sql = "Select U.subtotal,U.cantidad_productos,U.nombre from usuario U where id=?";
        List<Map<String, Object>> datosUsuario = conexion.queryForList(sql, id);

        //info de los productos
        sql = "SELECT P.nombre, DC.cantidad, P.precio FROM PRODUCTOS P JOIN DETALLES_CARRITO DC ON (P.id=DC.Productos_id) WHERE DC.Usuario_id=?";
        List<Map<String, Object>> datosProductos = conexion.queryForList(sql, id);

        Map<String, Object> resultado = null;

        // Verificar si hay algún resultado en el encabezado
        if (!datosUsuario.isEmpty()) {
            resultado = datosUsuario.get(0);

            // Agregar la lista de productos al encabezado
            resultado.put("Productos", datosProductos);
        }

        return datosUsuario.isEmpty() ? Collections.emptyMap() : resultado;
    }

}
