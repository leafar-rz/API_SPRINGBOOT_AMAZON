package mx.tecnm.itlp.AmazonAPI.models;

import java.util.List;

// importaciones para poder usar lombok
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor

public class ConsultaUnidad {
    private double subtotal;
    private int cantidadProductos;
    private String nombre;
    private List<ConsultaCarritoProdcutosUsuarios2> productos;
}
