import java.util.Scanner;

public class Practica2 {
//---

    //--- Tamaño Máximo
    final int TAMANIO_MAXIMO = 10; //Dimensión

    //--- Contadores - Reportes
    int contadorClientes = 0, contadorPeliculas = 0, contadorPrestamos = 0;

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
                case 3 -> { System.out.println("\n-----       Mostrar Info. Peliculas       -----"); mostrarInfoPeliculas(); }
                case 4 -> { System.out.println("\n-----       Ingreso de Peliculas (Crear)       -----"); crearPeliculaNueva(); }
                case 5 -> { System.out.println("\n-----       Ordenar Peliculas (A-Z)       -----"); }
                case 6 -> { System.out.println("\n-----       Ingreso de Clientes (Crear)       -----"); crearClienteNuevo();}
                case 7 -> { System.out.println("\n-----       Mostrar Info. Clientes       -----"); mostrarInfoClientes(); }
                case 8 -> { System.out.println("\n-----       Reportes       -----"); }
                case 9 -> { System.out.println("\n-----       Feliz Dia UwU       -----\n");}
                default -> {System.out.println("\n-----       Error - Opcion Invalida :c       -----\n");}
            }
        }while (opcion != 9);
    }

    //--- Método para Prestamos de Películas
    public void prestamosDePeliculas(){
        int idCliente;
        int idPelicula, cantidadDias;

        if (nuevoIDP != 0){ //Si hay películas
            System.out.println("* Lista de Peliculas *");
            for (int i = 0; i < TAMANIO_MAXIMO; i++) {
                if (disponible[i]){
                    System.out.println("ID ["+idPeliculas[i]+"]" +
                            "\tNombre: ["+nombresPeliculas[i]+"]" +
                            "\t  Categoria: ["+categorias[i]+"] "+
                            "\t  Disponible: ["+disponible[i]+"] ");
                }
            }

            System.out.println("\n* Ingrese el ID del cliente que prestara la pelicula: ");
            idCliente = sc.nextInt();
            if (clienteExistente(idCliente) != -1){ //Si ID existe
                if (clienteExistente(idCliente) != 33){ //Si ID (Cliente) no tiene prestada ningúna película
                    System.out.print("* ID de la pelicula: ");
                    idPelicula = sc.nextInt();
                    System.out.println("* Cantidad de dias de prestamo: ");
                    cantidadDias = sc.nextInt();

                }else { //Si ID (Cliente) si tiene prestada ningúna película

                }
            } else { //Si ID no existe
                System.out.println("* El ID no esta registrado *\n");
            }

        } else {

        }
    }

    //--- Método para crear Peliculas
    public void crearPeliculaNueva(){
        String nombre, opcion, categoria;
        int idPeli;

        do {

            System.out.print("Id: ");
            idPeli = sc.nextInt();
            sc.nextLine();
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
            System.out.print("Categoria: ");
            categoria = sc.nextLine();

            if (!verificarPelicula(idPeli)) {
                asignarPelicula(idPeli, nombre, categoria);
            } else {
                System.out.println("* El ID ya ha sido registrado *");
                System.out.println("* Error *");
            }

            System.out.println("\n* Desea Ingresar otra Pelicula *");
            System.out.println("* Si (S) / No (N) *");
            opcion = sc.nextLine();

        }while (opcion.equals("S"));

    }

    //--- Método para mostrar información de Películas
    public void mostrarInfoPeliculas(){
        for (int i = 0; i < TAMANIO_MAXIMO; i++) {
            if (nombresPeliculas[i] == null){
                continue;
            }
            System.out.println("ID ["+idPeliculas[i]+"]" +
                    "\tNombre: ["+nombresPeliculas[i]+"]" +
                    "\t  Categoria: ["+categorias[i]+"] "+
                    "\t  Disponible: ["+disponible[i]+"] ");
        }
    }


    //--- Método para crear Clientes
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
                System.out.println("* El ID ya ha sido registrado *");
                System.out.println("* Error *");
            }

            System.out.println("\n* Desea Ingresar otro Usuario *");
            sc.nextLine();
            System.out.println("* Si (S) / No (N) *");
            opcion = sc.nextLine();

        }while (opcion.equals("S"));

    }

    //--- Método para mostrar información de Clientes
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

    //-------------------------------------------
    public static void main(String[] args) {
        //--- Código Ejecutable
        new Practica2("Practica No 2\n");
    }
    //-------------------------------------------

    //--- Método para verificar el ID - Clientes
    public boolean verificarCliente(int id){
        boolean idRepetido = false;

        for (int i = 0; i < TAMANIO_MAXIMO; i++) {
            if (idClientes[i] == id){
                idRepetido = true;
            }
        }
        return idRepetido;
    }

    //--- Método para verificar el ID - Peliculas
    public boolean verificarPelicula(int id){
        boolean idRepetido = false;

        for (int i = 0; i < TAMANIO_MAXIMO; i++) {
            if (idPeliculas[i] == id){
                idRepetido = true;
            }
        }
        return idRepetido;
    }


    public int clienteExistente(int id){
        for (int i = 0; i < TAMANIO_MAXIMO; i++) {
            if (idCliente[i] == id) { //Si ID existe
                if (!prestados[i]) { //Prestados - False (No tiene películas prestadas)
                    return i; //Retornamos la posición del cliente
                } else { //Prestados - True (Si tiene películas prestadas)
                    return 33; //Retornamos un valor random
                }
            }
        }
        return -1; //Si ID no existe
    }

    //--- Método complemento para crear Clientes (Método Auxiliar)
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

    //--- Método complemento para crear Peliculas (Método Auxiliar)
    public void asignarPelicula(int id, String nombre, String categoria){
        if (nuevoIDP < TAMANIO_MAXIMO){
            this.idPeliculas[nuevoIDP++] = id;
        }
        if (nuevoNombreP < TAMANIO_MAXIMO){
            this.nombresPeliculas[nuevoNombreP++] = nombre;
        }
        if (nuevaCate < TAMANIO_MAXIMO){
            this.categorias[nuevaCate++] = categoria;
        }
        if (nuevoDispo < TAMANIO_MAXIMO){
            this.disponible[nuevoDispo++] = true;
        }
    }

}
