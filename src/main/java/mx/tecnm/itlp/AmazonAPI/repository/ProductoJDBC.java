package mx.tecnm.itlp.AmazonAPI.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.itlp.AmazonAPI.models.Productos;

@Repository
public class ProductoJDBC {
    @Autowired
    JdbcTemplate conexion;

    String sql;

    public List<Productos> consultarProductos(int id) throws Exception {
        sql = "select * from productos p join categorias c on (p.Categorias_id=c.id) where c.id=? AND c.activo=1;";
        return conexion.query(sql, new ProductoRM(),id);
    }
}
