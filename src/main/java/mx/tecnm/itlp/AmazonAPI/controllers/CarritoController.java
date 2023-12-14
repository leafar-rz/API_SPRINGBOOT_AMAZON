package mx.tecnm.itlp.AmazonAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;// para poder usar los List
import java.util.Map;

import mx.tecnm.itlp.AmazonAPI.models.Detalles_carrito;//importamos el modelo de Detalles_carrito
import mx.tecnm.itlp.AmazonAPI.repository.Detalles_carritoJDBC;
import mx.tecnm.itlp.AmazonAPI.models.ConsultaCarritoProdcutosUsuarios2;
import mx.tecnm.itlp.AmazonAPI.models.ConsultaCarritoProductosUsuarios;//importamos el modelo de ConsultaCarritoProdu

@RestController
@RequestMapping("/carrito")

public class CarritoController {

    @Autowired
    Detalles_carritoJDBC repo;

    //List<Detalles_carrito> detalles_carrito;

    /*
     *5 ENDPOINTS DE ESTE MIERCOLES EN 8
     * Para el manejo del carrito
     * Diseñe y construya los endpoints para le manejo de un carrito de compras, debe incluir:
     * 1- Insertar productos al carrito de compras
     * 2- Cambiar la cantidad de productos del carrito
     * 3- Quitar un producto del carrito
     * 4- Agregar productos al carrito que ya existen en el carrito (si ya existen que incremente la cantidad)
     * 5- Consultar el subtotal, cantidad de productos, nombre del usuario 
     * y productos que se han agregado al carrito mostrando la descripcion, cantidad y precio, 
     * seleccionadolo por su id del usuario
     */

   

    //1- Insertar productos al carrito de compras
    
    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody Detalles_carrito detalles_carrito) {
         try {

            System.out.println(detalles_carrito.getProductos_id());
            repo.insertar(detalles_carrito.getCantidad(),detalles_carrito.getPrecio(),detalles_carrito.getProductos_id(),detalles_carrito.getUsuario_id());
            
            return new ResponseEntity<>("La producto se agrego con exito", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo agregar el producto", HttpStatus.CONFLICT);
        }

    }

    //2- Cambiar la cantidad de productos del carrito
    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable int id,@RequestParam int cantidad) {
         
           int resultado= repo.update(id,cantidad);

            if(resultado>0){
                return new ResponseEntity<>("El carrito se actualizo con exito", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("No se pudo actualizar el carrito", HttpStatus.CONFLICT);
            }
    }

    //3- Quitar un producto del carrito
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        int resultado= repo.delete(id);
        if(resultado>0){
                return new ResponseEntity<>("El articulo fue eliminado con exito", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("No se pudo eliminar el articulo", HttpStatus.CONFLICT);
            }
    }

    //4- Agregar productos al carrito que ya existen en el carrito (si ya existen que incremente la cantidad)
    @PostMapping("/SinRepetir")
    public ResponseEntity<String> insertarSinRepetir(@RequestBody Detalles_carrito detalles_carrito) {
         try {
            //System.out.println(("Hola"));
            repo.insertarSinRepetir(detalles_carrito.getCantidad(),detalles_carrito.getPrecio(),detalles_carrito.getProductos_id(),detalles_carrito.getUsuario_id());
            return new ResponseEntity<>("La producto se agrego con exito", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo agregar el producto", HttpStatus.CONFLICT);
        }

    }


    /*5- Consultar el subtotal, cantidad de productos, nombre del usuario 
     * y productos que se han agregado al carrito mostrando la descripcion, cantidad y precio, 
     * seleccionadolo por su id del usuario*/

     @GetMapping("/get/{id}")
    public ResponseEntity<List<?>> consultar2(@PathVariable int id) throws Exception {
        
         List<?> resultado = null;
         resultado =repo.consultar(id);

         if(resultado.size()==0)
         {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         else
         {
            return new ResponseEntity<List<?>>(resultado, HttpStatus.OK);
         }
       
        
    }


    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> consultar(@PathVariable int id) throws Exception{
        Map<String, Object> resultado=null;

        resultado = repo.consultar2(id);

        if(resultado.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         else
         {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
         }
    }
    
    

    
    
}
