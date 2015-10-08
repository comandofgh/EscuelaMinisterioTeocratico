package util;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "Estudiante")
public class Estudiante implements Serializable{

    @DatabaseField(generatedId = true)
    private int id_estudiante;  //Primary Key

    @DatabaseField(index = true, canBeNull = false)
    private String nombre;

    @DatabaseField(canBeNull = false)
    private String apellido;

    @DatabaseField(canBeNull = false)
    private boolean sexo;


    /**
     * El motor de ORMlite requiere este constructor vacío para poder instanciar objetos de
     * esta clase por medio del API Reflection
     */
    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, boolean sexo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;

        //TODO: agregar una imagen default a la mecanica de uso por cada estudiante

    }

    //<editor-fold desc="GETTER METHODS">
    public int getId_estudiante() {
        return id_estudiante;
    }

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
