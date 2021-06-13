import java.util.Scanner;

public class Practica2 {
//---

    //--- Tamaño Máximo
    final int TAMANIO_MAXIMO = 10; //Dimensión

    //--- Clientes
    int[] idClientes = new int[TAMANIO_MAXIMO];
    String[] nombres = new String[TAMANIO_MAXIMO];
    int[] telefonos = new int[TAMANIO_MAXIMO];
    boolean[] prestados = new boolean[TAMANIO_MAXIMO];
    int nuevoID, nuevoNombre, nuevoTel; //Contadores de la cantidad ingresada en los arreglos

    //--- Peliculas
    int[] idPeliculas = new int[TAMANIO_MAXIMO];
    String[] nombresPeliculas = new String[TAMANIO_MAXIMO];
    String[] categorias = new String[TAMANIO_MAXIMO];
    boolean[] disponible = new boolean[TAMANIO_MAXIMO];
    int nuevoIDP, nuevoNombreP, nuevaCate, nuevoDispo; //Contadores de la cantidad ingresada en los arreglos

    //--- Memorabilia (Prestamos)
    int[] idCliente = new int[TAMANIO_MAXIMO];
    int[] idPelicula = new int[TAMANIO_MAXIMO];
    int[] cantDias = new int[TAMANIO_MAXIMO];
    int nuevoIDCliente, nuevoIDPelicula, nuevoCantDias; //Contadores de la cantidad ingresada en los arreglos


    Scanner sc = new Scanner(System.in);


    //--- Constructor Practica2
    public Practica2(String nombre) {
        System.out.println(nombre);
        initPractica();
    }

    //--- Método de Inicialización
    private void initPractica() {
        int opcion;

        do {

            System.out.println("\n-----       Menu Principal       -----");
            System.out.println("1.  * Prestamo de Peliculas *");
            System.out.println("2.  * Devolucion de Paliculas *");
            System.out.println("3.  * Mostrar las Peliculas *");
            System.out.println("4.  * Ingreso de Peliculas *");
            System.out.println("5.  * Ordenas Peliculas (A-Z) *");
            System.out.println("6.  * Ingresar Clientes Nuevos *");
            System.out.println("7.  * Mostrar Clientes *");
            System.out.println("8.  * Reportes *");
            System.out.println("9.  * Salir *");

            System.out.print("Opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> { System.out.println("\n-----       Prestamo de Peliculas       -----"); }
                case 2 -> { System.out.println("\n-----       Devolucion de Peliculas       -----"); }
                case 3 -> { System.out.println("\n-----       Mostrar Info. Peliculas       -----"); }
                case 4 -> { System.out.println("\n-----       Ingreso de Peliculas (Crear)       -----"); }
                case 5 -> { System.out.println("\n-----       Ordenar Peliculas (A-Z)       -----"); }
                case 6 -> { System.out.println("\n-----       Ingreso de Clientes (Crear)       -----"); crearClienteNuevo();}
                case 7 -> { System.out.println("\n-----       Mostrar Info. Clientes       -----"); mostrarInfoClientes(); }
                case 8 -> { System.out.println("\n-----       Reportes       -----"); }
                case 9 -> { System.out.println("\n-----       Feliz Dia UwU       -----\n");}
                default -> {System.out.println("\n-----       Error - Opcion Invalida :c       -----\n");}
            }
        }while (opcion != 9);
    }

    public void crearClienteNuevo(){
        String nombre, opcion;
        int id, telefono;

        do {

            System.out.print("Id: ");
            id = sc.nextInt();
            sc.nextLine();
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
            System.out.print("Telefono: ");
            telefono = sc.nextInt();

            if (!verificarCliente(id)) {
                asignarCliente(id, nombre, telefono);
            } else {
                System.out.println("-----       El ID ya ha sido registrado       -----");
                System.out.println("           -----       Error       -----");
            }

            System.out.println("\n-----       Desea Ingresar otro Usuario       -----");
            sc.nextLine();
            System.out.println("   -----       Si (S) / No (N)       -----");
            opcion = sc.nextLine();

        }while (opcion.equals("S"));

    }

    public void mostrarInfoClientes(){
        for (int i = 0; i < TAMANIO_MAXIMO; i++) {
            if (nombres[i] == null){
                continue;
            }
            System.out.println("ID ["+idClientes[i]+"]" +
                    "\tNombre: ["+nombres[i]+"]" +
                    "\tTelefono: ["+telefonos[i]+"] "+
                    "\tPelicula Prestada: ["+prestados[i]+"] ");
        }
    }

    public void asignarCliente(int id, String nombre, int telefono){
        if (nuevoID < TAMANIO_MAXIMO){
            this.idClientes[nuevoID++] = id;
        }
        if (nuevoNombre < TAMANIO_MAXIMO){
            this.nombres[nuevoNombre++] = nombre;
        }
        if (nuevoTel < TAMANIO_MAXIMO){
            this.telefonos[nuevoTel++] = telefono;
        }
    }


    public static void main(String[] args) {
        //--- Código Ejecutable
        new Practica2("Practica No 2\n");
    }

    //--- Método para verificar el ID
    public boolean verificarCliente(int id){
        boolean idRepetido = false;

        for (int i = 0; i < TAMANIO_MAXIMO; i++) {
            if (idClientes[i] == id){
                idRepetido = true;
            }
        }
        return idRepetido;
    }
}
