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
                case 1 -> { System.out.println("\n-----       Prestamo de Peliculas       -----"); prestamosDePeliculas(); }
                case 2 -> { System.out.println("\n-----       Devolucion de Peliculas       -----"); devolucionDePeliculas(); }
                case 3 -> { System.out.println("\n-----       Mostrar Info. Peliculas       -----"); mostrarInfoPeliculas(); }
                case 4 -> { System.out.println("\n-----       Ingreso de Peliculas (Crear)       -----"); crearPeliculaNueva(); }
                case 5 -> { System.out.println("\n-----       Ordenar Peliculas (A-Z)       -----"); ordenarPeliculas(); }
                case 6 -> { System.out.println("\n-----       Ingreso de Clientes (Crear)       -----"); crearClienteNuevo(); }
                case 7 -> { System.out.println("\n-----       Mostrar Info. Clientes       -----"); mostrarInfoClientes(); }
                case 8 -> { System.out.println("\n-----       Reportes       -----"); mostrarReportes(); }
                case 9 -> { System.out.println("\n-----       Feliz Dia UwU       -----\n");}
                default -> {System.out.println("\n-----       Error - Opcion Invalida :c       -----\n");}
            }
        }while (opcion != 9);
    }
//====================================================================================================================== Prestar
    //--- Método para Prestamos de Películas
    public void prestamosDePeliculas(){
        int idCliente;
        int idPelicula, cantidadDias;

        if (nuevoIDP != 0){ //Si hay películas
            System.out.println("* Lista de Peliculas Disponibles *");
            for (int i = 0; i < TAMANIO_MAXIMO; i++) {
                if (disponible[i]){ // Si disponible = true
                    System.out.println("ID ["+idPeliculas[i]+"]" +
                            "\tNombre: ["+nombresPeliculas[i]+"]" +
                            "\t  Categoria: ["+categorias[i]+"] "+
                            "\t  Disponible: ["+disponible[i]+"] ");
                }
            }

            System.out.println("\n* Ingrese el ID del cliente que prestara la pelicula: ");
            idCliente = sc.nextInt();
            if (verificarCliente(idCliente)){ //Si ID existe
                if (verificarExistenciaRenta(idCliente)){ //Si ID (Cliente) no tiene prestada ningúna película
                    System.out.print("* ID de la pelicula: ");
                    idPelicula = sc.nextInt();
                    System.out.println("* Cantidad de dias de prestamo: ");
                    cantidadDias = sc.nextInt();
                    asignarPrestamo(idCliente, idPelicula, cantidadDias);
                    System.out.println("*-*  Prestamo Completado  *-*");
                }else { //Si ID (Cliente) si tiene prestada ningúna película
                    System.out.println("* Cliente ya cuenta con una pelicula rentada *");
                }
            } else { //Si ID no existe
                System.out.println("* El ID no esta registrado *\n");
            }

        } else {
            System.out.println("* Aun no hay registro de peliculas *\n");
        }
    }

//====================================================================================================================== Devolución
    //--- Método para la Devolución de Películas
    public void devolucionDePeliculas(){
        int idCliente;
        int idPelicula;

        if (nuevoIDCliente != 0) {
            System.out.println("* Devolucion de peliculas - Ingrese los siguintes datos *");
            System.out.print("* ID Cliente: ");
            idCliente = sc.nextInt();
            if (verificarCliente(idCliente)) {
                if (verificarExistenciaRenta(idCliente)) {
                    System.out.println("* Cliente no cuenta con un prestamo * - No necesita devolver nada -");
                } else {
                    System.out.println("* ID de la pelicula: ");
                    idPelicula = sc.nextInt();
                    asignarDevolucion(idCliente, idPelicula);
                    System.out.println("*-* Devolucion completada *-*");
                }
            } else {
                System.out.println("* El ID no esta registrado *\n");
            }
        } else {
            System.out.println("* Aun no hay registro de Clientes *\n");
        }
    }

//====================================================================================================================== Películas
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
        System.out.println("");
    }

//====================================================================================================================== Ordenar
    //--- Método para Ordenar las Películas (A-Z)
    public void ordenarPeliculas() {
        String aux;

        System.out.println("*-* Catalogo de Peliculas *-*");
        if (nuevoIDP != 0) {
            for (int i = 0; i < nuevoIDP - 1; i++) { //Ordenar Películas (A-Z)
                for (int j = i + 1; j < nuevoIDP; j++) {
                    if (nombresPeliculas[i].compareTo(nombresPeliculas[j]) > 0) {
                        aux = nombresPeliculas[i];
                        nombresPeliculas[i] = nombresPeliculas[j];
                        nombresPeliculas[j] = aux;
                    }
                }
            }

            for (int i = 0; i < TAMANIO_MAXIMO; i++) { //Mostrar Películas (A-Z)
                if (nombresPeliculas[i] == null) {
                    continue;
                }
                System.out.println("Nombre: [" + nombresPeliculas[i] + "]");
            }
            System.out.println("");


        } else {
            System.out.println("* Aun no hay registro de peliculas *\n");
        }
    }

//====================================================================================================================== Clientes
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
        System.out.println("");
    }
//====================================================================================================================== Reportes
    //--- Método para mostrar Reportes
    public void mostrarReportes(){
        int opcion;

        do {

            System.out.println("\n-----       Menu Reportes       -----");
            System.out.println("1.  * Cantidad de Peliculas por Categoria *");
            System.out.println("2.  * Peliculas de una Categoria en especifico *");
            System.out.println("3.  * Cantidad de Peliculas Prestadas *");
            System.out.println("4.  * Pelicula mas prestada *");
            System.out.println("5.  * Pelicula menos prestada *");
            System.out.println("6.  * Regresar *");

            System.out.print("Opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> { System.out.println("\n-----       Cantidad de Peliculas por Categoria       -----"); cantidadPeliCategorias(); }
                case 2 -> { System.out.println("\n-----       Peliculas de una Categoria en especifico       -----"); peliculaEnCategoriaEspe(); }
                case 3 -> { System.out.println("\n-----       Cantidad de Peliculas Prestadas       -----"); cantidadPeliPrestadas(); }
                case 4 -> { System.out.println("\n-----       Pelicula mas prestada       -----"); peliculaMasPrestada(); }
                case 5 -> { System.out.println("\n-----       Pelicula menos prestada       -----"); pelicularMenosPrestada(); }
                case 6 -> { System.out.println("\n-----       Regresando       -----"); }
                default -> {System.out.println("\n-----       Error - Opcion Invalida :c       -----\n");}
            }
        }while (opcion != 6);
    }

    //--- Método para la cantidad de películas por cada categoría
    public void cantidadPeliCategorias(){
        String[] cateAux = new String[TAMANIO_MAXIMO];
        int contador;

        for (int i = 0; i < nuevaCate; i++) {
            cateAux[i] = categorias[i]; //Copia
        }

        if (nuevaCate != 0) {
            for (int i = 0; i < nuevaCate; i++) {
                contador = 0;
                for (int j = 0; j < nuevaCate; j++) {
                    if (cateAux[i].equals(categorias[j])){
                        cateAux[i] = categorias[i];
                        contador++;
                    }
                }
                if (cateAux[i] == null){
                    continue;
                }
                if (cateAux[i].equals(cateAux[i+1])){ //Las ignora si están seguidas
                    continue;
                }
                System.out.println("* Categoria ["+cateAux[i]+"] - Cantidad Peliculas ["+contador+"]");
            }
        } else {
            System.out.println("* Aun no hay registro de peliculas *\n");
        }
    }

    //--- Método para las películas dentro de una categoría
    public void peliculaEnCategoriaEspe(){
        if (nuevaCate != 0) {

        } else {
            System.out.println("* Aun no hay registro de peliculas *\n");
        }
    }

    //--- Método para establecer la cantidad de películas prestadas
    public void cantidadPeliPrestadas(){

    }

    //--- Método de las películas más prestadas
    public void peliculaMasPrestada(){

    }

    //--- Método de las películas menos prestadas
    public void pelicularMenosPrestada(){

    }

    //------------------------------------------- MAIN
    public static void main(String[] args) {
        //--- Código Ejecutable
        new Practica2("Practica No 2\n");
    }
    //------------------------------------------- MAIN

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

    //--- Método para verificar el ID - Clientes
    public boolean verificarPelicula(String categoria){
        boolean categoriaRepetida = false;

        for (int i = 0; i < nuevaCate; i++) {
            if (categorias[i].equals(categoria)){
                categoriaRepetida = true;
            }
        }
        return categoriaRepetida;
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

    //--- Método para verificar si Cliente ya cuenta con una película rentada
    public boolean verificarExistenciaRenta(int id){
        boolean existeRenta = false;

        for (int i = 0; i < TAMANIO_MAXIMO; i++) {
            if (idClientes[i] == id){
                if (!prestados[i]){
                    existeRenta = true;
                }
            }
        }
        return existeRenta;
    }

//====================================================================================================================== Complementos
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
        contadorClientes++;
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
        contadorPeliculas++;
    }

    //--- Método complemento para crear el Préstamo de Películas
    public void asignarPrestamo(int idCliente, int idPelicula, int canditidadDias){
        prestados[encontrarIDCliente(idCliente)] = true; //Cambiamos el estado de préstamo del cliente
        disponible[encontrarIDPelicula(idPelicula)] = false; //Cambiamos el estado de préstamo de la película

        if (nuevoIDCliente < TAMANIO_MAXIMO){
            this.idCliente[nuevoIDCliente++] = idCliente;
        }
        if (nuevoIDPelicula < TAMANIO_MAXIMO){
            this.idPelicula[nuevoIDPelicula++] = idPelicula;
        }
        if (nuevoCantDias < TAMANIO_MAXIMO){
            this.cantDias[nuevoCantDias++] = canditidadDias;
        }
        contadorPrestamos++;
    }

    //--- Método complemento para retornar una película prestada/rentada
    public void asignarDevolucion(int idCliente, int idPelicula){
        prestados[encontrarIDCliente(idCliente)] = false; //Cambiamos el estado de préstamos del cliente
        disponible[encontrarIDPelicula(idPelicula)] = true; //Cambiamos el estado de préstamo de la película
    }

//======================================================================================================================
    //--- Método para devolver el ID del Cliente
    public int encontrarIDCliente(int id){
        int idCliente = 0;
        for (int i = 0; i < TAMANIO_MAXIMO; i++) {
            if (idClientes[i] == id){
                idCliente = i;
            }
        }
        return idCliente;
    }

    //--- Método para devolver el ID de la Película
    public int encontrarIDPelicula(int id){
        int idPelicula = 0;
        for (int i = 0; i < TAMANIO_MAXIMO; i++) {
            if (idPeliculas[i] == id){
                idPelicula = i;
            }
        }
        return idPelicula;
    }

}
