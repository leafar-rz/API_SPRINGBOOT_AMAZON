package mx.tecnm.itlp.AmazonAPI.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.itlp.AmazonAPI.models.ConsultaUnidad;
import mx.tecnm.itlp.AmazonAPI.models.ConsultaCarritoProdcutosUsuarios2;

public class ConsultaUnidadRM implements RowMapper<ConsultaUnidad> {
    @Override
    public ConsultaUnidad mapRow(ResultSet rs, int rowNum) throws SQLException {
        ConsultaUnidad unidad = new ConsultaUnidad();
        unidad.setSubtotal(rs.getDouble("U.subtotal"));
        unidad.setCantidadProductos(rs.getInt("U.cantidad_productos"));
        unidad.setNombre(rs.getString("U.nombre"));

        // Lista para almacenar los productos de la segunda consulta
        List<ConsultaCarritoProdcutosUsuarios2> productos = new ArrayList<>();

        // Agregar productos a la lista
        do {
            ConsultaCarritoProdcutosUsuarios2 producto = new ConsultaCarritoProdcutosUsuarios2();
            producto.setPrecio(rs.getDouble("P.precio"));
            producto.setCantidad(rs.getInt("DC.cantidad"));
            producto.setNombre(rs.getString("P.nombre"));
            productos.add(producto);
        } while (rs.next());

        // Establecer la lista de productos en la unidad
        unidad.setProductos(productos);

        return unidad;
    }
}
