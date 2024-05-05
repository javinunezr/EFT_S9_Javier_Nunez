/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eft_s9_javier_nunez;

/**
 *
 * @author javier
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class EFT_S9_Javier_Nunez {

    /**
     * @param args the command line arguments
     */
    
        // Variables estáticas y globales
    static int precioVIP = 30000;
    static int precioPalco = 25000;
    static int precioPlatea = 20000;
    static int precioGaleria = 15000;
    static int descuentoEstudiante = 10;
    static int descuentoTerceraEdad = 15;

    // Estructuras de datos para almacenar información de ventas, asientos, clientes y precios finales
    static ArrayList<String> ventas = new ArrayList<>();
    static ArrayList<String> asientosDisponiblesVIP = new ArrayList<>();
    static ArrayList<String> asientosDisponiblesPalco = new ArrayList<>();
    static ArrayList<String> asientosDisponiblesPlatea = new ArrayList<>();
    static ArrayList<String> asientosDisponiblesGaleria = new ArrayList<>();
    static ArrayList<String> clientes = new ArrayList<>();
    static ArrayList<Integer> preciosFinales = new ArrayList<>();
    static ArrayList<String> idsVentas = new ArrayList<>();
    static HashMap<String, String> ventasPorEventoAsiento = new HashMap<>();

    
    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("Bienvenido al Teatro Moro");

     // Inicializar la lista de asientos disponibles con la cantidad correspondiente
    for (int i = 0; i < 30; i++) {
        asientosDisponiblesVIP.add("VIP-" + (i + 1));
    }
    for (int i = 0; i < 100; i++) {
        asientosDisponiblesPalco.add("PALCO-" + (i + 1));
    }
    for (int i = 0; i < 200; i++) {
        asientosDisponiblesPlatea.add("PLATEA-" + (i + 1));
    }
    for (int i = 0; i < 500; i++) {
        asientosDisponiblesGaleria.add("GALERIA-" + (i + 1));
    }


    boolean continuar = true;
    while (continuar) {
        System.out.println("\n----- Menú Principal -----");
        System.out.println("1. Comprar entrada");
        System.out.println("2. Ver resumen de ventas");
        System.out.println("3. Editar entradas");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                comprarEntrada(scan);
                break;
            case 2:
                verResumenVentas();
                break;
            case 3:
                modificarEntrada(scan);
                break;
            case 4:
                continuar = false;
                System.out.println("¡Gracias por visitar el Teatro Moro!");
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
        }
    }
}
    
    public static void comprarEntrada(Scanner scan) {
    System.out.println("\nSeleccione el evento al que desea asistir:");
    System.out.println("1. Obra de teatro Romeo y Julieta");
    System.out.println("2. Concierto Los Bunkers");
    System.out.println("3. Stand up Edo Caroe");
    int opcionEvento = scan.nextInt();

    // Mostrar la cantidad de asientos disponibles para cada tipo de asiento
    System.out.println("Cantidad de asientos disponibles:");
    System.out.println("VIP: " + asientosDisponiblesVIP.size());
    System.out.println("Palco: " + asientosDisponiblesPalco.size());
    System.out.println("Platea: " + asientosDisponiblesPlatea.size());
    System.out.println("Galería: " + asientosDisponiblesGaleria.size());

    String nombreEvento = obtenerNombreEvento(opcionEvento);
    if (nombreEvento.equals("")) {
        System.out.println("Opción de evento no válida.");
        return;
    }

    System.out.println("\nIndique la categoría de asiento que desea comprar:");
    System.out.println("1. VIP");
    System.out.println("2. Palco");
    System.out.println("3. Platea");
    System.out.println("4. Galería");
    int opcionCategoria = scan.nextInt();
    String tipoDeEntrada = "";

    switch (opcionCategoria) {
        case 1:
            tipoDeEntrada = "VIP";
            break;
        case 2:
            tipoDeEntrada = "PALCO";
            break;
        case 3:
            tipoDeEntrada = "PLATEA";
            break;
        case 4:
            tipoDeEntrada = "GALERIA";
            break;
        default:
            System.out.println("Opción de categoría no válida.");
            return;
    }

    // Restar la cantidad de asientos disponibles en la categoría seleccionada
    String asiento = obtenerAsientoDisponible(opcionEvento, tipoDeEntrada);
    if (asiento.equals("")) {
        System.out.println("No hay asientos disponibles en la categoría seleccionada.");
        return;
    }

    int precioBase = obtenerPrecioBase(opcionEvento, tipoDeEntrada);
    int descuento = obtenerDescuento(scan);

    double precioFinal = precioBase * (1 - (double) descuento / 100);

    // Generar un ID de venta único
    String idVenta = UUID.randomUUID().toString();

    // Agregar el asiento vendido
    ventasPorEventoAsiento.put(idVenta, nombreEvento + "-" + tipoDeEntrada + "-" + asiento);

    // Agregar la venta a la lista de ventas
    ventas.add("ID de venta: " + idVenta + ", Evento: " + nombreEvento + ", Ubicación: " + tipoDeEntrada.toUpperCase() + ", Total a pagar por la entrada: $" + (int) precioFinal + ", Descuento aplicado: " + descuento + "%");

    // Registrar al cliente
    clientes.add("Edad: " + (2024 - 2000));

    // Imprimir boleta
    System.out.println("------------------------------------");
    System.out.println("             Teatro Moro            ");
    System.out.println("------------------------------------");
    System.out.println("ID de venta: " + idVenta);
    System.out.println("Evento: " + nombreEvento);
    System.out.println("Ubicación: " + tipoDeEntrada.toUpperCase());
    System.out.println("Costo base: $" + precioBase);
    System.out.println("Descuento aplicado: " + descuento + "%");
    System.out.println("Costo final: $" + (int) precioFinal);
    System.out.println("------------------------------------");
    System.out.println("Gracias por su visita al Teatro Moro");
    System.out.println("------------------------------------");
}
        
    public static void verResumenVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No se han realizado ventas aún.");
            return;
        }

        System.out.println("\n----- Resumen de Ventas -----");
        for (String venta : ventas) {
            System.out.println(venta);
        }
    }

    public static void modificarEntrada(Scanner scan) {
        System.out.println("\nIngrese el ID de venta que desea modificar:");
        String idVentaModificar = scan.next();

        boolean encontrado = false;
        for (int i = 0; i < ventas.size(); i++) {
            String venta = ventas.get(i);
            if (venta.contains("ID de venta: " + idVentaModificar)) {
                encontrado = true;
                String[] detalles = venta.split(", ");
                String ubicacion = detalles[2].substring(detalles[2].indexOf(": ") + 2).toLowerCase();
                String asiento = detalles[3].substring(detalles[3].indexOf(": ") + 2);
                int opcionEvento = obtenerOpcionEvento(detalles[1].substring(detalles[1].indexOf(": ") + 2));

                // Eliminar la venta original
                ventas.remove(i);

                // Devolver el asiento a la lista de asientos disponibles
                switch (ubicacion) {
                    case "vip":
                        asientosDisponiblesVIP.add(asiento);
                        break;
                    case "palco":
                        asientosDisponiblesPalco.add(asiento);
                        break;
                    case "platea":
                        asientosDisponiblesPlatea.add(asiento);
                        break;
                    case "galeria":
                        asientosDisponiblesGaleria.add(asiento);
                        break;
                }

                // Volver a comprar la entrada
                comprarEntrada(scan);

                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ninguna venta con el ID proporcionado.");
        }
    }

    public static int obtenerDescuento(Scanner scan) {
        System.out.println("Por favor ingrese su edad:");
        int edad = scan.nextInt();
        int descuento = 0;
        if (edad <= 18) {
            descuento = descuentoEstudiante;
        } else if (edad >= 65) {
            descuento = descuentoTerceraEdad;
        }
        return descuento;
    }

    public static String obtenerNombreEvento(int opcionEvento) {
        switch (opcionEvento) {
            case 1:
                return "Obra de teatro Romeo y Julieta";
            case 2:
                return "Concierto Los Bunkers";
            case 3:
                return "Stand up Edo Caroe";
            default:
                return "";
        }
    }

    public static int obtenerOpcionEvento(String nombreEvento) {
        switch (nombreEvento) {
            case "Obra de teatro Romeo y Julieta":
                return 1;
            case "Concierto Los Bunkers":
                return 2;
            case "Stand up Edo Caroe":
                return 3;
            default:
                return 0;
        }
    }
    
    public static String obtenerAsientoDisponible(int opcionEvento, String tipoDeEntrada) {
    switch (tipoDeEntrada) {
        case "VIP":
            return asientosDisponiblesVIP.isEmpty() ? "" : asientosDisponiblesVIP.remove(0);
        case "PALCO":
            return asientosDisponiblesPalco.isEmpty() ? "" : asientosDisponiblesPalco.remove(0);
        case "PLATEA":
            return asientosDisponiblesPlatea.isEmpty() ? "" : asientosDisponiblesPlatea.remove(0);
        case "GALERIA":
            return asientosDisponiblesGaleria.isEmpty() ? "" : asientosDisponiblesGaleria.remove(0);
        default:
            return "";
    }
}
    
    public static int obtenerPrecioBase(int opcionEvento, String tipoDeEntrada) {
    switch (opcionEvento) {
        case 1: // Obra de teatro Romeo y Julieta
            switch (tipoDeEntrada) {
                case "VIP":
                    return precioVIP;
                case "PALCO":
                    return precioPalco;
                case "PLATEA":
                    return precioPlatea;
                case "GALERIA":
                    return precioGaleria;
                default:
                    return 0;
            }
        case 2: // Concierto Los Bunkers
            switch (tipoDeEntrada) {
                case "VIP":
                    return precioVIP + 10000; // Precio modificado para Los Bunkers
                case "PALCO":
                    return precioPalco + 10000; // Precio modificado para Los Bunkers
                case "PLATEA":
                    return precioPlatea + 10000; // Precio modificado para Los Bunkers
                case "GALERIA":
                    return precioGaleria + 10000; // Precio modificado para Los Bunkers
                default:
                    return 0;
            }
        case 3: // Stand up Edo Caroe
            switch (tipoDeEntrada) {
                case "VIP":
                    return precioVIP - 5000; // Precio modificado para Stand up Edo Caroe
                case "PALCO":
                    return precioPalco - 5000; // Precio modificado para Stand up Edo Caroe
                case "PLATEA":
                    return precioPlatea - 5000; // Precio modificado para Stand up Edo Caroe
                case "GALERIA":
                    return precioGaleria - 5000; // Precio modificado para Stand up Edo Caroe
                default:
                    return 0;
            }
        default:
            return 0;
    }
}
}
