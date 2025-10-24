package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Computer {

    private String serial;
    private int piso;
    private int columna;
    private final List<Incident> incidentes;

    public Computer(String serial, int piso, int columna) {
        this.serial = serial;
        this.piso = piso;
        this.columna = columna;
        this.incidentes = new ArrayList<>();
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serialNumber) {
        this.serial = serialNumber;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int floor) {
        this.piso = floor;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int row) {
        this.columna = row;
    }

    // Devuelve una lista inmutable de los incidentes asociados al computador
    public List<Incident> getIncidentes() {
        return Collections.unmodifiableList(incidentes);
    }

    public void addIncident(Incident incident) {
        incidentes.add(incident);
    }

}
