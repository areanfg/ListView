package app.android.gaf004.listview.models;

import android.graphics.drawable.Drawable;

public class Fruta {
    private String nombre;
    private String origen;
    private Drawable icono;

    public Fruta(String nombre, String origen, Drawable icono){

        this.nombre = nombre;
        this.icono = icono;
        this.origen = origen;
    }

    public String getNombre(){
        return nombre;
    }
    public Drawable getIcono(){
        return icono;
    }
    public String getOrigen(){
        return origen;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setIcono(Drawable icono){
        this.icono = icono;
    }
    public void setOrigen(String origen){
        this.origen = origen;
    }

}
