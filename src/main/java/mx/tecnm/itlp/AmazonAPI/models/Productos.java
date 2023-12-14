package mx.tecnm.itlp.AmazonAPI.models;

// importaciones para poder usar lombok
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor

public class Productos {
    int id;
    String nombre;
    double precio;
    String color;
    String marca;
    String descripcion;
    double peso;
    String dimensiones;
    int Categorias_id;
}
