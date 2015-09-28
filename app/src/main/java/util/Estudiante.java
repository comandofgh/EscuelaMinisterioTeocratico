package util;

import java.io.Serializable;

public class Estudiante implements Serializable{
    private String nombre, apellido;
    private boolean sexo;

    public Estudiante(String nombre, String apellido, boolean sexo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;

    }

    //<editor-fold desc="GETTER METHODS">
    public boolean isSexo() {
        return sexo;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }
    //</editor-fold>

    //<editor-fold desc="SETTER METHODS">
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
    //</editor-fold>

    //<editor-fold desc="EQUALS Y HASHCODE">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estudiante that = (Estudiante) o;

        if (sexo != that.sexo) return false;
        if (!nombre.equals(that.nombre)) return false;
        return !(apellido != null ? !apellido.equals(that.apellido) : that.apellido != null);

    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        result = 31 * result + (sexo ? 1 : 0);
        return result;
    }
    //</editor-fold>
    //Garantizan que la seleccion sea la misma que creamos
}
