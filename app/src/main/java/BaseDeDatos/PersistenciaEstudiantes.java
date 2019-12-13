package BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import Entidades.Estudiante;

public class PersistenciaEstudiantes {
    private static final String DATABASE="promedios.db";
    private  static final int VERSION=1;

    private SQLiteDatabase db;
    private HelperSQLite helpeDB;
    public PersistenciaEstudiantes(Context context)
    {
        this.helpeDB=new HelperSQLite(context,DATABASE,null,VERSION);
    }

    private boolean Abrir ()throws SQLiteException
    {
        this.db=this.helpeDB.getWritableDatabase();
        return true;
    }
    private boolean Cerrar()throws SQLiteException
    {
        this.db.close();
        return  true;
    }
    public boolean Insert(Estudiante estudiante)throws SQLiteException
    {
        ContentValues valorInsertar=new ContentValues();
        valorInsertar.put(Estudiante.FIELDS[1],estudiante.getNombre());
        valorInsertar.put(Estudiante.FIELDS[2],estudiante.getEdad());
         this.Abrir();
         if(this.db.insert(Estudiante.TABLA,null,valorInsertar)>0)
         {
             this.Cerrar();
             return true;
         }
         this.Cerrar();
        return false;
    }

    public boolean Update(Estudiante estudianete)throws SQLiteException
    {
        ContentValues valorUpdate=new ContentValues();
        valorUpdate.put(Estudiante.FIELDS[1],estudianete.getNombre());
        valorUpdate.put(Estudiante.FIELDS[2],estudianete.getEdad());
        this.Abrir();
        if(this.db.update(Estudiante.TABLA,valorUpdate, Estudiante.FIELDS[0]+"="+Integer.toString(estudianete.getID()),null)>0)
        {
            this.Cerrar();
            return true;
        }
        this.Cerrar();
        return false;
    }
    public boolean Delete(Estudiante estudiante)throws SQLiteException
    {
        this.Abrir();
        if(this.db.delete(Estudiante.TABLA, Estudiante.FIELDS[0]+"="+ Integer.toString(estudiante.getID()),null)>0)
        {
            this.Cerrar();
            return true;
        }
        this.Cerrar();
        return false;
    }

    public Estudiante getEstudiante(int id)
    {
        this.Abrir();
        Cursor cursorEstudiantes=this.db.query(Estudiante.TABLA, Estudiante.FIELDS, Estudiante.FIELDS[0]+"="+Integer.toString(id),null,null,null,null);
        if(cursorEstudiantes!=null&&cursorEstudiantes.getCount()>0)
        {
            cursorEstudiantes.moveToFirst();
            Estudiante item=new Estudiante();

            item.setID(cursorEstudiantes.getInt(0));
            item.setNombre(cursorEstudiantes.getString(1));
            item.setEdad(cursorEstudiantes.getInt(2));
            this.Cerrar();
            return item;
        }
        this.Cerrar();
        return null;
    }

    public List<Estudiante> getAll()
    {
        this.Abrir();
        List<Estudiante> lista=new ArrayList<>();
        Cursor cursorEstudiantes=this.db.query(Estudiante.TABLA, Estudiante.FIELDS,null,null,null,null,null);

        if(cursorEstudiantes!=null&&cursorEstudiantes.getCount()>0)
        {
            cursorEstudiantes.moveToFirst();
            do {
                //Creamos el libro donde almacenaremos la información
                Estudiante item=new Estudiante();

                item.setID(cursorEstudiantes.getInt(0));
                item.setNombre(cursorEstudiantes.getString(1));
                item.setEdad(cursorEstudiantes.getInt(2));
                //Agregamos el libro a la lista
                lista.add(item);
            }while(cursorEstudiantes.moveToNext());

            this.Cerrar();
            return lista;
        }
        this.Cerrar();
        return lista;
    }
}
