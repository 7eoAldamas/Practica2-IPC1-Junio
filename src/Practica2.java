import java.util.Scanner;

public class Practica2 {
//---

    //--- Tamaño Máximo
    final int TAMANIO_MAXIMO = 30; //Dimensión

    //--- Clientes
    int[] idClientes;
    String[] nombres;
    int[] telefonos;
    boolean[] prestados;
    int nuevoID, nuevoNombre, nuevoTel;
    Scanner sc = new Scanner(System.in);

    //--- Peliculas
    int[] idPeliculas;
    String[] nombresPeliculas;
    String[] categorias;
    boolean[] disponible;
    int nuevoIDP, nuevoNombreP, nuevaCate, nuevoDispo;


    //--- Constructor Practica2
    public Practica2(String nombre) {
        System.out.println(nombre);
        initPractica();
    }

    private void initPractica() {
    }

    public static void main(String[] args) {
        //--- Código Ejecutable
        new Practica2("Practica No 2\n");
    }
}
