package mx.tecnm.itlp.AmazonAPI.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.itlp.AmazonAPI.models.Categoria;

public class CategoriaRM implements RowMapper<Categoria>{

    @Override
    public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
        Categoria categoria=new Categoria();
        categoria.setId(rs.getInt("id"));
        categoria.setNombre(rs.getString("nombre"));
        return categoria;
    }
    
}
