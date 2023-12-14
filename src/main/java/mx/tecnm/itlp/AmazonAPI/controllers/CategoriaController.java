package mx.tecnm.itlp.AmazonAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;// para poder usar los List
import mx.tecnm.itlp.AmazonAPI.models.Categoria;//importamos el modelo de categorias
import mx.tecnm.itlp.AmazonAPI.repository.CategoriaJDBC;

import mx.tecnm.itlp.AmazonAPI.models.Productos;
import mx.tecnm.itlp.AmazonAPI.repository.ProductoJDBC;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaJDBC repo;

    @Autowired
    ProductoJDBC repositorio;

    @GetMapping
    public List<Categoria> consultar() throws Exception {
        return repo.consultar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(@PathVariable int id) throws Exception {
        // creamos un objeto de la clase categoria
        Categoria resultado = null;
        try {
            // asignamos el valor de la consulta al objeto de la clase
            resultado = repo.buscar(id);

            return new ResponseEntity<Categoria>(resultado, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping
    public ResponseEntity<String> insertar(@RequestParam String nombre) {
         try {
            repo.insertar(nombre);
            return new ResponseEntity<>("La categoria se agrego con exito", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo actualizar la categoria", HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifica(@PathVariable int id,@RequestParam String nombre) {
         
           int resultado= repo.update(nombre,id);

            if(resultado>0){
                return new ResponseEntity<>("La categoria se actualizo con exito", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("No se pudo actualizar la categoria", HttpStatus.CONFLICT);
            }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> desactivar(@PathVariable int id) {
        int resultado= repo.desactivar(id);
        if(resultado>0){
                return new ResponseEntity<>("La categoria fue eliminada con exito", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("No se pudo eliminar la categoria", HttpStatus.CONFLICT);
            }
    }

     @GetMapping("/{id}/productos")
    public ResponseEntity<List<Productos>> consultarProductos(@PathVariable int id) throws Exception {
        List<Productos> resultados;

        try {
            resultados = repositorio.consultarProductos(id);
    
            if (resultados != null && !resultados.isEmpty()) {
                return new ResponseEntity<>(resultados, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    































        
    }

   /*  @PostMapping("/{nombre}")
    public ResponseEntity<Categoria> postCategory(@PathVariable String nombre) {
        Categoria resultado = null;

         try {
           
            resultado = repo.postCategory(nombre);

            return new ResponseEntity<Categoria>(resultado, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    } */

    @GetMapping("/2")
    public String consultar2() throws Exception {
        return "HOLA";
    }
}
