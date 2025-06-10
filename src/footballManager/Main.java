package footballManager;

import java.io.*;
import java.util.*;

/**
 * Clase principal del programa de fútbol.
 * Aquí empieza el programa y muestra el menú.
 */

public class Main {
    /** Archivo donde se guarda el mercado de fichajes. */
    public static final String FILE_PATH = "src/footballManager/mercat_fitxatges.txt";

    /** Lista con todos los jugadores. */
    public static List<Jugador> jugadores = new ArrayList<>();

    /** Lista con todos los equipos. */
    public static List<Equip> equipos = new ArrayList<>();

    /** Lista con todos los entrenadores. */
    public static List<Entrenador> entrenadores = new ArrayList<>();

    /** Liga que se está jugando. */
    public static Lliga ligaActual;

    /** Lista con personas en el mercado de fichajes. */
    public static List<Persona> mercadoFichajes = new ArrayList<>();

    /**
     * Método principaol.
     * Muestra el menú y responde según la opción del usuario.
     * @param args No se usa.
     */
    public static void main(String[] args) {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        int opcion ;

        anadirDatos();

        cargarMercadoFichajes();

        do {

            menuPrincipal();

            System.out.println("Introduce la opción:");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    mostrarClassificacion();
                    break;
                case 2:
                    gestionarEquip();
                    break;
                case 3:
                    crearNuevoEquipo();
                    break;
                case 4:
                    altaPersona();
                    break;
                case 5:
                    consultarEquips();
                    break;
                case 6:
                    consultarDadesJugador();
                    break;
                case 7:
                    disputarNuevaLiga();
                    break;
                case 8:
                    realizarSesionEntrenamiento();
                    break;
                case 9:
                    transferirJugador();
                    break;
                case 10:
                    guardarDatos();
                    break;
                case 0:
                    System.out.println("Saliendo......");
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion no valida...");
                    break;
            }
        }while (!exit);

    }
    /**
     * Crea equipos, jugadores y entrenadores de ejemplo.
     */
    public static void anadirDatos() {
        Equip barcelona = new Equip("Barcelona", 1990, "Barcelona", "Camp Nou", "Joan Laporta");
        Equip madrid = new Equip("Madrid", 1950, "Madrid", "Camp", "Joel Glazer");
        Equip bayern = new Equip("Bayern Munich", 1900, "Munich", "Allianz Arena", "Herbert Hainer");

        equipos.add(barcelona);
        equipos.add(madrid);
        equipos.add(bayern);

        Jugador jugador1 = new Jugador("Alan", "Conejo", "24/06/1999", 15000000, 11, "Delantero");
        Jugador jugador2 = new Jugador("Carlos", "Fernandez", "15/08/1995", 90000000, 9, "Delantero");
        Jugador jugador3 = new Jugador("Adrian", "Lopez", "02/03/1998", 75000000, 8, "Centrocampista");
        Jugador jugador4 = new Jugador("Roberto", "Gomez", "20/11/1993", 60000000, 5, "Defensa");
        Jugador jugador5 = new Jugador("Miguel", "Sanchez", "10/07/1990", 45000000, 1, "Portero");
        Jugador jugador6 = new Jugador("Javier", "Ruiz", "28/09/2000", 80000000, 7, "Extremo");

        Entrenador entrenador = new Entrenador("Carlos", "Sanchez", "01/01/1980", 900000000, 5, true);
        barcelona.anadirJugador(jugador1);
        barcelona.anadirJugador(jugador2);
        barcelona.anadirJugador(jugador3);

        madrid.anadirJugador(jugador4);
        madrid.anadirJugador(jugador5);
        madrid.setEntrenador(entrenador);
        bayern.anadirJugador(jugador6);
    }

    /**
     * Buscar un jugador por nombre y dorsal en un equipo.
     * Pide los datos al usuario.
     */
    public static void consultarDadesJugador() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del equipo a consultar: ");
        String nomEquip = scanner.nextLine().trim();

        Equip equipTrobat = null;
        for (Equip equip : equipos) {
            if (equip.getNombre().equalsIgnoreCase(nomEquip)) {
                equipTrobat = equip;
                break;
            }
        }

        if (equipTrobat == null) {
            System.out.println("Error: El equipo no se encuentra en el sistema.");
            return; //main menu
        }

        System.out.print("Ingrese el nombre del jugador: ");
        String nomJugador = scanner.nextLine().trim();
        System.out.print("Ingrese el dorsal del jugador: ");
        int dorsalJugador;
        try {
            dorsalJugador = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: El dorsal debe ser un número válido.");
            return;
        }

        Jugador jugadorTrobat = null;
        for (Jugador jugador : equipTrobat.getJugadores()) {
            if (jugador.getNombre().equalsIgnoreCase(nomJugador) && jugador.getDorsal() == dorsalJugador) {
                jugadorTrobat = jugador;
            }
        }

        if (jugadorTrobat == null) {
            System.out.println("Error: No se encontró el jugador en el equipo.");
        } else {
            System.out.println("Datos del jugador encontrado:");
            System.out.println(jugadorTrobat);
        }
    }

    /**
     * Dar de alta un nuevo jugador o entrenador.
     * Pide los datos al usuario y lo añade al mercado.
     */
    public static void altaPersona() {
        Scanner scanner = new Scanner(System.in);
        String tipo, nombre, apellido, fechaNacimiento, posicion;
        double sueldoAnual;

        System.out.println("¿Quieres dar de alta a un jugador (J) oa un entrenador (E) o salir (N)? ");
        tipo = scanner.nextLine().trim().toUpperCase();
        if (tipo.equals("N")) {
            System.out.println("Saliendo del proceso de alta de personas.");
            return;
        }

        System.out.print("Nombre: ");
        nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        apellido = scanner.nextLine();
        System.out.print("Fecha de nacimiento (dd/mm/yyyy): ");
        fechaNacimiento = scanner.nextLine();
        System.out.print("Sueldo anual: ");
        sueldoAnual = scanner.nextDouble();
        scanner.nextLine();

        if (tipo.equals("J")) {
            System.out.print("dorsal: ");
            int dorsal = scanner.nextInt();
            scanner.nextLine();

            posicion = obtenerPosicionValida(scanner);

            Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, sueldoAnual, dorsal, posicion);
            mercadoFichajes.add(jugador);
            System.out.println("Jugador añadido correctamente!");

        } else if (tipo.equals("E")) {
            System.out.print("Número de torneos ganados: ");
            int numeroTorneos = scanner.nextInt();
            scanner.nextLine();

            System.out.print("¿Es seleccionador nacional? (S/N): ");
            boolean esNacional = scanner.nextLine().trim().equalsIgnoreCase("S");

            Entrenador entrenador = new Entrenador(nombre, apellido, fechaNacimiento, sueldoAnual, numeroTorneos, esNacional);
            mercadoFichajes.add(entrenador);
            System.out.println("Entrenador añadido correctamente!");

        } else {
            System.out.println("Opción no valida. Por favor, introduce 'J' para jugador o 'E' para entrenador.");
        }
    }
    /**
     * Pide una posición válida del jugador.
     */

    //Prompt the user to enter the dorsal until the input value is valid
    public static String obtenerPosicionValida(Scanner scanner) {
        String posicion;
        while (true) {
            System.out.print("Ingrese la posición del jugador " + Arrays.toString(Jugador.POSICIONS) + ": ");
            posicion = scanner.nextLine().toUpperCase();

            if (esPosicionValida(posicion)) {
                return posicion;
            }
            System.out.println("Posición invalida. Debe ser una de " + Arrays.toString(Jugador.POSICIONS) + ". Intenta otra vez.");
        }
    }


    /**
     * Comprueba si la posición es correcta.
     */
    public static boolean esPosicionValida(String posicion) {
        for (String pos : Jugador.POSICIONS) {
            if (pos.equals(posicion)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Entrenan todos los jugadores y entrenadores.
     */

    //执行entrenar
    public static void realizarSesionEntrenamiento() {
        System.out.println("Iniciando sesión de entrenamiento...");

        for (Jugador jugador : jugadores) {
            jugador.entrenar();
            jugador.cambioPosicio();
        }

        for (Entrenador entrenador : entrenadores) {
            entrenador.entrenar();
            entrenador.incrementarSou();
        }

        System.out.println("Sesión de entrenamiento realizada con éxito.");
    }

    /**
     * Crea un equipo nuevo si no existe.
     */

    public static void crearNuevoEquipo() {
        Scanner sc = new Scanner(System.in);
        String nombre, ciudad, estadio = null, presidente, respuesta;
        int any;

        System.out.println("Nombre para nuevo equipo: ");
        nombre = sc.nextLine().trim();

        if (buscarEquip(nombre) != null) {
            System.out.println("Error, equipo ya existe.");
            return;
        }

        System.out.println("Año de fundación: ");
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, ingrese un año válido:");
            sc.next();
        }
        any = sc.nextInt();
        sc.nextLine();

        System.out.println("Ciudad: ");
        ciudad = sc.nextLine().trim();

        System.out.println("¿Quiere introducir estadio? (S/N): ");
        respuesta = sc.nextLine().trim().toUpperCase();


        while (!respuesta.equals("S") && !respuesta.equals("N")) {
            System.out.println("Opción no válida. Introduzca 'S' para sí o 'N' para no:");
            respuesta = sc.nextLine().trim().toUpperCase();
        }

        if (respuesta.equals("S")) {
            System.out.println("Nombre del estadio: ");
            estadio = sc.nextLine().trim();
        }

        System.out.println("Nombre del presidente: ");
        presidente = sc.nextLine().trim();

        equipos.add(new Equip(nombre, any, ciudad, estadio, presidente));
        System.out.println("¡Equipo creado correctamente!");
    }

    /**
     * Crea una liga y juega partidos.
     */

    public static void disputarNuevaLiga() {
        Scanner sc = new Scanner(System.in);
        String nomLliga, nomEquip;
        int numEquips, golesLocal, golesVisitante;

        System.out.print("Nombre de la nueva liga: ");
        nomLliga = sc.nextLine();

        System.out.print("Numero de equipos participants: ");
        numEquips = sc.nextInt();
        sc.nextLine();


        Lliga liga = new Lliga(nomLliga);

        for (int i = 0; i < numEquips; i++) {
            System.out.print("Nombre de equipo " + (i + 1) + ": ");
            nomEquip = sc.nextLine();
            Equip nuevoEquip = new Equip(nomEquip);

            liga.anadirEquipo(nuevoEquip);
        }


        for (int i = 0; i < numEquips; i++) {
            for (int j = i + 1; j < numEquips; j++) {
                Equip equipoLocal = liga.getEquipos().get(i);
                Equip equipoVisitante = liga.getEquipos().get(j);
                golesLocal = (int) (Math.random() * 5);
                golesVisitante = (int) (Math.random() * 5);

                Partit nuevoPartido = new Partit(equipoLocal, equipoVisitante);

                nuevoPartido.jugarPartido(golesLocal, golesVisitante);

                liga.programarPartido(nuevoPartido);
            }
        }

        liga.mostrarClasificacion();
    }
    /**
     * Muestra la clasificación de la liga.
     */

    public static void mostrarClassificacion() {
        if (ligaActual == null) {
            System.out.println("No hay ninguna liga.");
            return;
        }
        ligaActual.mostrarClasificacion();

    }
    /**
     * Busca y muestra un equipo.
     */

    public static void consultarEquips() {
        Scanner sc = new Scanner(System.in);
        String nombre;
        System.out.println("Introduce el nombre del equipo que deseas buscar:");
        nombre = sc.nextLine();

        Equip equip = null;
        for (Equip e : equipos) {
            if (e.getNombre().equals(nombre)) {
                equip = e;
            }
        }

        if (equip != null) {
            System.out.println("Equipo encontrado: " + equip);
        } else {
            System.out.println("Equipo no encontrado.");
            menuPrincipal();
        }

    }
    /**
     * Gestiona un equipo si existe.
     */
    public static void gestionarEquip() {
        Scanner sc = new Scanner(System.in);
        String nombre;
        System.out.println("Qué equipo quiere gestionar?");
        nombre = sc.nextLine();

        Equip equip = buscarEquip(nombre);
        if (equip != null) {
            subMenu(equip);
        }
        else {
            System.out.println("No entontrado este equipo.");
        }

    }
    /** Muestra opciones para gestionar un equipo. */
    public static void subMenu(Equip equip) {
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("-------Submenú-------");
            System.out.println("Gestionar equip:");
            System.out.println("\t1- Donar de baixa equip");
            System.out.println("\t2- Modificar president/a");
            System.out.println("\t3- Destituir entrenador/a");
            System.out.println("\t4- Fitxar jugador/a o entrenador/a");
            System.out.println("0- Sortir");

            System.out.println("Introduzca tu opcion:");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    eliminarEquipo();
                    break;
                case 2:
                    modificarPresidente(equip);
                    break;
                case 3:
                    destituirEntrenador(equip);
                    break;
                case 4:
                    ficharPersona(equip);
                    break;
            }
        }while (option != 0);

    }

    /** Elimina el entrenador de un equipo. */
    public static void destituirEntrenador(Equip equip) {
        Scanner sc = new Scanner(System.in);
        if (equip.getEntrenador() == null) {
            System.out.println("Este equipo ya no tiene entrenador.");
            return;
        }

        System.out.print("Estàs segur que vols destituir l'entrenador " + equip.getEntrenador().getNombre() + " (S/N)? ");
        String confirmation = sc.nextLine().toUpperCase();

        if (confirmation.equals("S")) {
            Entrenador entrenadorDestituir = equip.getEntrenador();
            equip.removeEntrenador();
            System.out.println("El entrenador " + entrenadorDestituir.getNombre() + " ha sido destituido.");

            mercadoFichajes.add(entrenadorDestituir);
        } else {
            System.out.println("Destitución cancelada.");
        }
    }

    /** Elimina un equipo si existe. */
    public static void eliminarEquipo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del equipo que desea eliminar: ");
        String nombreEquipo = scanner.nextLine().trim();

        Equip equipoEliminar = buscarEquip(nombreEquipo);

        if (equipoEliminar == null) {
            System.out.println("El equipo '" + nombreEquipo + "' no existe.");
            return;
        }

        System.out.println("¿Está seguro de que desea eliminar el equipo '" + nombreEquipo + "'? (S/N)");
        char confirmacion = scanner.next().toUpperCase().charAt(0);

        if (confirmacion == 'S') {
            equipos.remove(equipoEliminar);
            System.out.println("El equipo '" + nombreEquipo + "' ha sido eliminado con éxito.");
        } else {
            System.out.println("Operación cancelada. El equipo no ha sido eliminado.");
        }

    }

    /** Busca un equipo por su nombre. */
    public static Equip buscarEquip(String nombre) {
        for (Equip e : equipos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    /** Cambia el presidente de un equipo. */
    public static void modificarPresidente(Equip equip) {
        Scanner sc = new Scanner(System.in);
        String newPresidente;

        if (equip.getPresidente() == null) {
            System.out.println("El equipo no hay presidente asignado.");
        }else {
            System.out.println("El presidente actual es " + equip.getPresidente());
        }

        System.out.println("Ingrese el nombre de presidente: ");
        newPresidente = sc.nextLine();

        if (newPresidente.trim().isEmpty()) {
            System.out.println("El nombre del presidente no puede estar vacío.");
        }else {
            equip.setPresidente(newPresidente);
            System.out.println("El presidente ha sido cambiado a: " + newPresidente);
        }

    }

    /** Carga los jugadores y entrenadores desde archivo. */
    public static void cargarMercadoFichajes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {//逐行阅读

            String linea;
            String nombre;
            String apellido;
            String fechaNacimiento;
            String posicion;
            double sueldoAnual;
            int calidad, torneosGanados, dorsal;
            boolean haSidoSeleccionador;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos[0].equals("J")) {
                    nombre = datos[1];
                    apellido = datos[2];
                    fechaNacimiento = datos[3];
                    sueldoAnual = Double.parseDouble(datos[5]);
                    dorsal = Integer.parseInt(datos[6]);
                    posicion = datos[7];
                    calidad = Integer.parseInt(datos[8]);

                    Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, sueldoAnual, dorsal, posicion, calidad);
                    mercadoFichajes.add(jugador);
                    System.out.println("Jugador: " + nombre + " " + apellido + ", Fecha de nacimiento: " + fechaNacimiento +
                            ", Suledo anual: " + String.format("%.1f", sueldoAnual) + ", Dorsal: " + dorsal + ", Posicion: " + posicion + ", Calidad: " + calidad);

                }
                else if (datos[0].equals("E")) {
                    nombre = datos[1];
                    apellido = datos[2];
                    fechaNacimiento = datos[3];
                    sueldoAnual = Double.parseDouble(datos[5]);
                    torneosGanados = Integer.parseInt(datos[6]);
                    haSidoSeleccionador = Boolean.parseBoolean(datos[7]);

                    Entrenador entrenador = new Entrenador(nombre, apellido, fechaNacimiento, sueldoAnual, torneosGanados, haSidoSeleccionador);
                    mercadoFichajes.add(entrenador);
                    System.out.println("Entrenador: " + nombre + " " + apellido + ", Fecha de nacimiento: " + fechaNacimiento +
                            ", Suledo anual: " +String.format("%.1f", sueldoAnual) + ", Numero de torneos ganados: " + torneosGanados + ", Es nacional: " + haSidoSeleccionador);
                }
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo de mercado.");
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de los datos del archivo de mercado.");
        }
    }

    /** Muestra personas disponibles para fichar. */
    public static void mostrarMercadoFichajes() {
        System.out.println("Mercado de fichajes:");
        for (Persona p : mercadoFichajes) {
            System.out.println(p.toString());
        }
    }

    /** Ficha un jugador o entrenador desde el mercado. */
    public static void ficharPersona(Equip equip) {
        //将用户创建的equip传参到这个方法中
        Scanner sc = new Scanner(System.in);

        mostrarMercadoFichajes();

        if (mercadoFichajes.isEmpty()) {
            System.out.println("No hay personas disponibles para fichar.");
            return;
        }

        System.out.print("\nIngrese el nombre de la persona que desea fichar: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Ingrese el apellido: ");
        String apellido = sc.nextLine().trim();

        Persona personaFichada = null;

        for (Persona p : mercadoFichajes) {
            if (p.getNombre().equalsIgnoreCase(nombre) && p.getApellido().equalsIgnoreCase(apellido)) {
                personaFichada = p;
                break;
            }
        }

        if (personaFichada != null) {
            mercadoFichajes.remove(personaFichada);

            if (personaFichada instanceof Jugador) {
                equip.getJugadores().add((Jugador) personaFichada); //arraylist加错了
                System.out.println("Has fichado al jugador: " + personaFichada.getNombre() + " " + personaFichada.getApellido());
            } else if (personaFichada instanceof Entrenador) {
                equip.setEntrenador((Entrenador) personaFichada);
                System.out.println("Has fichado al entrenador: " + personaFichada.getNombre() + " " + personaFichada.getApellido());
            }

            actualizarArchivo();
        } else {
            System.out.println("No se encontró la persona en el mercado.");
        }
    }

    /** Guarda el mercado en un archivo. */
    public static void actualizarArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Persona p : mercadoFichajes) {
                if (p instanceof Jugador j) {
                    writer.write("J;" + j.getNombre() + ";" + j.getApellido() + ";" +
                            j.getFechaNacimiento() + ";" + j.getMotivacion() + ";" +
                            j.getSueldoAnual() + ";" + j.getDorsal() + ";" +
                            j.getPosicion() + ";" + j.getCalidad());
                } else if (p instanceof Entrenador e) {
                    writer.write("E;" + e.getNombre() + ";" + e.getApellido() + ";" +
                            e.getFechaNacimiento() + ";" + e.getMotivacion() + ";" +
                            e.getSueldoAnual() + ";" + e.getNumeroTorneos() +
                            ";" + e.isEsNacional());
                }
                writer.newLine();
            }
            System.out.println("Archivo actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }

    /** Guarda los datos de los equipos en un archivo. */
    public static void guardarDatos() {
        //FileWriter 在出现任何 IO 失败的情况下会抛出 IOException
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            for (Equip e : equipos) {
                fw.write(e.toFileFormat());
            }
            System.out.println("Archivo guardado correctamente.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /** Muestra el menú principal. */
    public static void menuPrincipal() {
        System.out.println("Welcome to Politècnics Football Manager:");
        System.out.println("\t1- Veure classificació lliga actual \uD83C\uDFC6");
        System.out.println("\t2- Gestionar equip ⚽");
        System.out.println("\t3- Donar d'alta equip");
        System.out.println("\t4- Donar d'alta jugador/a o entrenador/a");
        System.out.println("\t5- Consultar dades equip");
        System.out.println("\t6- Consultar dades jugador/a equip");
        System.out.println("\t7- Disputar nova lliga");
        System.out.println("\t8- Realitzar sessió entrenament (mercat fitxatges)");
        System.out.println("\t9- Transferir jugador/a");
        System.out.println("\t\t\t10- Desar dades equips");
        System.out.println("\t0- Sortir");
    }

    /** Transfiere un jugador de un equipo a otro. */
    public static void transferirJugador() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre del equipo original: ");
        Equip equipOriginal = buscarEquip(sc.nextLine());

        if (equipOriginal == null) {
            System.out.println("Equipo original no encontrado.");
            return;
        }

        System.out.print("Nombre del equipo destino: ");
        Equip equipoDestino = buscarEquip(sc.nextLine());

        if (equipoDestino == null) {
            System.out.println("Equipo destino no encontrado.");
            return;
        }

        System.out.print("Nombre del jugador a transferir: ");
        String nombreJugador = sc.nextLine();
        Jugador jugadorTransferir = equipOriginal.buscarJugadorPorNombre(nombreJugador);

        if (jugadorTransferir == null) {
            System.out.println("Jugador no encontrado en el equipo original.");
            return;
        }

        // Validar si el dorsal ya está ocupado en el equipo destino
        int dorsal = jugadorTransferir.getDorsal();
        if (equipoDestino.existeDorsal(dorsal)) {
            System.out.println("El dorsal " + dorsal + " está ocupado en el equipo destino.");
            dorsal = asignarNuevoDorsal(equipoDestino);
            if (dorsal == -1) {
                System.out.println("No se puede transferir el jugador. No hay dorsales disponibles.");
                return;
            }
        }

        equipOriginal.eliminarJugador(jugadorTransferir);
        jugadorTransferir.setDorsal(dorsal);
        equipoDestino.anadirJugador(jugadorTransferir);

        System.out.println("Jugador transferido con éxito con el dorsal " + dorsal + ".");
    }

    /** Asigna un dorsal libre al jugador. */
    public static int asignarNuevoDorsal(Equip equipo) {
        for (int i = 1; i <= 99; i++) {
            if (!equipo.existeDorsal(i)) {
                return i;
            }
        }
        return -1; // No hay dorsales disponibles
    }
}