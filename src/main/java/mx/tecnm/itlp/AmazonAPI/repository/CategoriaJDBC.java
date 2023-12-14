package mx.tecnm.itlp.AmazonAPI.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.itlp.AmazonAPI.models.Categoria;
import mx.tecnm.itlp.AmazonAPI.models.Productos;

@Repository
public class CategoriaJDBC {

    @Autowired
    JdbcTemplate conexion;

    String sql;

    public List<Categoria> consultar() throws Exception {
        sql = "SELECT id, nombre FROM categorias where activo=1";

        // return conexion.query(sql, new CategoriaRM());//Este CategoriaRM() no se
        // importa porque esta dentor del mismo paquete y las clases son publicas

        return conexion.query(sql, new CategoriaRM());
    }

    public Categoria buscar(int id) {
        sql = "SELECT id, nombre FROM categorias where id=? AND activo=1;";
        System.out.println(conexion.queryForObject(sql, new CategoriaRM(), id));
        return conexion.queryForObject(sql, new CategoriaRM(), id);
        
        // si tuviera dos parametros, osea buscar por dos cosas en el where seria asi
        // sql="SELECT id, nombre FROM categorias where id=? and nombre=?";
        // en el return en el queryforobject despues del RM se ponen los parametros en
        // el orden de la consulta
        // return conexion.queryForObject(sql, new CategoriaRM(), id, nombre);

    }

     public void insertar(String nombre) {
        sql = "insert into categorias(nombre) values(?);";
        conexion.update(sql, nombre);
    }

     public int update(String nombre, int id) {
        sql = "UPDATE categorias SET nombre = ? WHERE id = ? AND activo=1;";
        return conexion.update(sql, nombre,id);
    }

    public int desactivar(int id) {
        sql = "UPDATE categorias SET activo=0 WHERE id = ?;";
        return conexion.update(sql, id);
    }
    
    /* public Categoria insertar(String nombre) {

        sql = "insert into categorias(nombre) values(?);";
        conexion.update(sql, nombre);

        sql = "select * from categorias where nombre = ? order by id desc limit 1";
        return conexion.queryForObject(sql, new CategoriaRM(), nombre);

    } */

    public List<Productos> consultar2(int id) throws Exception {
        sql = "select * from productos p join categorias c on (p.Categorias_id=c.id) where c.id=? AND c.activo=1;";
        return conexion.query(sql, new ProductoRM(),id);
    }

}
