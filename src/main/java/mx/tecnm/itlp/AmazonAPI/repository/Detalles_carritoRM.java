package mx.tecnm.itlp.AmazonAPI.repository;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.itlp.AmazonAPI.models.Detalles_carrito;

public class Detalles_carritoRM implements RowMapper<Detalles_carrito>{
     @Override
    public Detalles_carrito mapRow(ResultSet rs, int rowNum) throws SQLException {
        Detalles_carrito detalle=new Detalles_carrito();
        detalle.setId(rs.getInt("id"));
        detalle.setCantidad(rs.getInt("DC.cantidad"));
        detalle.setPrecio(rs.getDouble("P.precio"));
        detalle.setProductos_id(rs.getInt("productos_id"));
        detalle.setUsuario_id(rs.getInt("usuario_id"));
        return detalle;
    }
}

