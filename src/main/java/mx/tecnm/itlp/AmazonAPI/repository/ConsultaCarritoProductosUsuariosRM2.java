package mx.tecnm.itlp.AmazonAPI.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.itlp.AmazonAPI.models.ConsultaCarritoProdcutosUsuarios2;

public class ConsultaCarritoProductosUsuariosRM2 implements RowMapper<ConsultaCarritoProdcutosUsuarios2>{
     @Override
    public ConsultaCarritoProdcutosUsuarios2 mapRow(ResultSet rs, int rowNum) throws SQLException {
        ConsultaCarritoProdcutosUsuarios2 detalle=new ConsultaCarritoProdcutosUsuarios2();
       detalle.setPrecio(rs.getDouble("precio"));
        detalle.setCantidad(rs.getInt("DC.cantidad"));
        detalle.setNombre(rs.getString("P.nombre"));
        return detalle;
        
    }
}
