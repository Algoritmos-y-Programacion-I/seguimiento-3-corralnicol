package model;

public class SchoolController {

    private final int pisos;
    private final int columnas;

    private final Computer[][] computadores;

    public SchoolController(int pisos, int columnas) {
        this.pisos = pisos;
        this.columnas = columnas;
        this.computadores = new Computer[pisos][columnas];
    }

    public String agregarComputador(String serial, int piso) {

        if (existeSerial(serial)) {
            return "El número de serie ya existe en el sistema.";
        }

        piso = piso - 1;

        var filaComputadores = computadores[piso];

        for (int i = 0; i < filaComputadores.length; i++) {
            if (filaComputadores[i] == null) {
                filaComputadores[i] = new Computer(serial, piso, i + 1);
                return "Computador agregado en el piso " + piso + ", columna " + (i + 1);
            }
        }

        return "No hay espacios disponibles en el piso " + piso;
    }

    public Incident agregarIncidenteEnComputador(String serial, String descripcion) {
        for (int p = 0; p < pisos; p++) {
            for (int c = 0; c < columnas; c++) {
                Computer pc = computadores[p][c];
                if (pc != null && pc.getSerial().equals(serial)) {
                    Incident incident = new Incident(descripcion);
                    pc.addIncident(incident);
                    return incident;
                }
            }
        }
        return null;
    }

    public Computer getComputadorConMasIncidentes() {
        Computer seleccionado = null;
        int max = -1;
        for (int p = 0; p < pisos; p++) {
            for (int c = 0; c < columnas; c++) {
                Computer pc = computadores[p][c];
                if (pc != null) {
                    int num = pc.getIncidentes().size();
                    if (num > max) {
                        max = num;
                        seleccionado = pc;
                    }
                }

            }
        }
        return seleccionado;
    }

    private boolean existeSerial(String serialNumber) {
        for (int p = 0; p < pisos; p++) {
            for (int c = 0; c < columnas; c++) {
                Computer pc = computadores[p][c];
                if (pc != null && pc.getSerial().equals(serialNumber)) {
                    return true;
                }
            }
        }

        return false;
    }

}
