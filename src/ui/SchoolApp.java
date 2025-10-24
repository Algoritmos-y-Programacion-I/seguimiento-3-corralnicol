package ui;

import java.util.Scanner;
import model.SchoolController;
import model.Computer;
import model.Incident;

public class SchoolApp {

    // 5 pisos, c/u es 1 fila de 10 columnas => 10 equipos por piso
    private final SchoolController controller = new SchoolController(5, 10);

    private final Scanner input;

    public static void main(String[] args) {
        SchoolApp ui = new SchoolApp();
        ui.menu();
    }

    // Constructor
    public SchoolApp() {
        input = new Scanner(System.in);
    }

    public void menu() {

        System.out.println("Bienvenido");

        int option = -1;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("--------------------------------------------------------");
            System.out.println("1) Registrar computador");
            System.out.println("2) Registrar incidente en computador");
            System.out.println("3) Consultar el computador con más incidentes");
            System.out.println("0) Salir del sistema");
            System.out.print("> ");
            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();
            } else {
                input.nextLine();
                option = -1;
            }

            switch (option) {
                case 1 -> registrarComputador();
                case 2 -> registrarIncidenteEnComputador();
                case 3 -> consultarComputadorConMasIncidentes();
                case 0 -> System.out.println("\nFin");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.");
            }

        } while (option != 0);
    }

    // Registrar computador
    public void registrarComputador() {
        System.out.println("\n== Registrar computador ==");
        System.out.print("Ingrese el # serial: ");
        String serial = input.nextLine().trim();

        System.out.print("Ingrese el piso (1-5): ");
        int floor = input.nextInt();
        input.nextLine();

        String msg = controller.agregarComputador(serial, floor);
        System.out.println(msg);
    }

    public void registrarIncidenteEnComputador() {
        System.out.println("\n== Registrar incidente ==");
        System.out.print("Ingrese el número serial: ");
        String serial = input.nextLine().trim();

        System.out.print("Descripción del incidente: ");
        String description = input.nextLine().trim();

        Incident inc = controller.agregarIncidenteEnComputador(serial, description);
        if (inc != null) {
            System.out.println("Incidente registrado para el serial " + serial);
        } else {
            System.out.println("No se encontró un computador con el serial " + serial);
        }
    }

    // Consultar el computador con más incidentes
    public void consultarComputadorConMasIncidentes() {
        System.out.println("\n== Computador con más incidentes ==");
        Computer pc = controller.getComputadorConMasIncidentes();
        if (pc == null) {
            System.out.println("No hay computadores registrados todavía");
            return;
        }
        System.out.println(
                "Serial: " + pc.getSerial() +
                        " | Piso: " + pc.getPiso() +
                        " | Fila: " + pc.getColumna() +
                        " | Incidentes: " + pc.getIncidentes().size());
    }
}
