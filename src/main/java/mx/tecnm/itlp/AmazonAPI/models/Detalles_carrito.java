package mx.tecnm.itlp.AmazonAPI.models;

// importaciones para poder usar lombok
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
public class Detalles_carrito {
    int id;
    int cantidad;
    double precio;
    double importe;
    int productos_id;
    int usuario_id;    
}
