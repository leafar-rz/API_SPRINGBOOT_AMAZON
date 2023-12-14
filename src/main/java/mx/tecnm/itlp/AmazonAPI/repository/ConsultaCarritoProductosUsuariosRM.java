package mx.tecnm.itlp.AmazonAPI.repository;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.itlp.AmazonAPI.models.ConsultaCarritoProductosUsuarios;

public class ConsultaCarritoProductosUsuariosRM implements RowMapper<ConsultaCarritoProductosUsuarios>{
     @Override
    public ConsultaCarritoProductosUsuarios mapRow(ResultSet rs, int rowNum) throws SQLException {
        ConsultaCarritoProductosUsuarios detalle=new ConsultaCarritoProductosUsuarios();
        detalle.setSubtotal(rs.getDouble("U.subtotal"));
       /* detalle.setPrecio(rs.getDouble("precio"));
        detalle.setCantidad(rs.getInt("DC.cantidad"));
        detalle.setNombre_producto(rs.getString("P.nombre"));*/
        detalle.setNombre_usuario(rs.getString("U.nombre"));
        detalle.setCantidad_productos(rs.getInt("U.cantidad_productos"));
        return detalle;

    }
}
