package mx.tecnm.itlp.AmazonAPI.models;
// importaciones para poder usar lombok
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor

public class ConsultaCarritoProductosUsuarios {
    double subtotal;
    /*int cantidad;
    double precio;*/
   // String nombre_producto;
    String nombre_usuario; 
    int cantidad_productos; 
}

