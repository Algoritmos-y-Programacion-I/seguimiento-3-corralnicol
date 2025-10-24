package model;

import java.time.LocalDate;

public class Incident {

    private LocalDate fecha;
    private String descripcion;
    private boolean solucionado;

    public Incident(String description) {
        this.descripcion = description;
        this.fecha = LocalDate.now();
        this.solucionado = false;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate dateReport) {
        this.fecha = dateReport;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String description) {
        this.descripcion = description;
    }

    public boolean getSolucionado() {
        return solucionado;
    }

    public void setSolucionado(boolean solution) {
        this.solucionado = solution;
    }

}

