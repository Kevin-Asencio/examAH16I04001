package Entidades;

public class Estudiante {

    public static final String TABLA="Estudiantes";
    public static final String QUERY_CREATE_TABLE="CREATE TABLE Estudiantes(ID INTEGER PRIMARY KEY AUTOINCREMENT,Nombre TEXT NOT NULL,Edad INTEGER NOT NULL)";
    public static final  String[] FIELDS={"ID","Nombre","Edad"};
    private int ID;
    private String Nombre;
    private int Edad;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }
}
