package mx.tecnm.itlp.AmazonAPI.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.itlp.AmazonAPI.models.Productos;

public class ProductoRM implements RowMapper<Productos>{

     @Override
    public Productos mapRow(ResultSet rs, int rowNum) throws SQLException {
        Productos producto=new Productos();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setColor(rs.getString("color"));
        producto.setMarca(rs.getString("marca"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPeso(rs.getDouble("peso"));
        producto.setDimensiones(rs.getString("dimensiones"));
        producto.setCategorias_id(rs.getInt("Categorias_id"));
        return producto;
    }

}
