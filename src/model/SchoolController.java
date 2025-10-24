package model;

/**
 * Administra la estructura de los computadores en pisos y columnas. 
 * Permite agregar computadores, registrar incidentes asociados a cada equipo
 * y determinar cuál computador ha tenido más incidentes.
 * 
 * <p>Esta clase actúa como un controlador central que gestiona las relaciones
 * entre los objetos {@link Computer} e {@link Incident}.</p>
 * 
 * @author Nicol
 */
public class SchoolController {

    /** Número total de pisos del edificio o estructura. */
    private final int pisos;

    /** Número de columnas (computadores por piso). */
    private final int columnas;

    /** Matriz bidimensional que almacena los computadores por piso y columna. */
    private final Computer[][] computadores;

    /**
     * Crea un nuevo controlador de escuela con una cantidad específica de pisos y columnas.
     *
     * @param pisos número de pisos disponibles para ubicar computadores.
     * @param columnas número de columnas (espacios) por piso.
     */
    public SchoolController(int pisos, int columnas) {
        this.pisos = pisos;
        this.columnas = columnas;
        this.computadores = new Computer[pisos][columnas];
    }

    /**
     * Agrega un nuevo computador en un piso específico, si hay espacio disponible y 
     * el número de serie no está repetido.
     *
     * @param serial número de serie único del computador.
     * @param piso número del piso donde se desea agregar el computador (empezando desde 1).
     * @return un mensaje indicando si el computador fue agregado correctamente o el motivo del fallo.
     */
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

    /**
     * Registra un nuevo incidente en el computador identificado por su número de serie.
     *
     * @param serial número de serie del computador donde ocurrió el incidente.
     * @param descripcion descripción del incidente ocurrido.
     * @return el objeto {@link Incident} creado, o {@code null} si no se encontró el computador.
     */
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

    /**
     * Busca y devuelve el computador con la mayor cantidad de incidentes registrados.
     *
     * @return el {@link Computer} con más incidentes, o {@code null} si no hay computadores registrados.
     */
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

    /**
     * Verifica si ya existe un computador con un número de serie determinado.
     *
     * @param serialNumber número de serie a verificar.
     * @return {@code true} si el número de serie ya está registrado; {@code false} en caso contrario.
     */
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
