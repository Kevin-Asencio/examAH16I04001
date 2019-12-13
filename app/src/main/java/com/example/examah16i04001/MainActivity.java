package com.example.examah16i04001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adaptadores.AdaptadorEstudiante;
import BaseDeDatos.PersistenciaEstudiantes;
import Entidades.Estudiante;
import ListenerItem.ListenerClick;

public class MainActivity extends AppCompatActivity {

    private ListenerClick OnClickItemlistener;
    List<Estudiante> lista;
    RecyclerView recyclerView;
    Button btnAgregar;
    AdaptadorEstudiante adaptadorEstudiante;
    PersistenciaEstudiantes dbEstudiantes;
    EditText edtNombre,edtEdad;
    Estudiante estudianteEditar;
    Button btnProcesar;
    boolean editar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lista=new ArrayList<Estudiante>();
        this.recyclerView=findViewById(R.id.rclLista);
        this.btnAgregar=findViewById(R.id.btnAgregar);
        this.edtNombre=findViewById(R.id.edtNombre);
        this.edtEdad=findViewById(R.id.edtEdad);
        this.btnProcesar=findViewById(R.id.btnProcesar);
        this.editar=false;
        CargarDatos();
        this.OnClickItemlistener=new ListenerClick() {
            @Override
            public void onClick(View v) {
                estudianteEditar=new Estudiante();
                estudianteEditar=lista.get((int)v.getTag());
                editar=true;
                edtEdad.setText(Integer.toString(estudianteEditar.getEdad()));
                edtNombre.setText(estudianteEditar.getNombre());
            }
        };
        this.adaptadorEstudiante = new AdaptadorEstudiante(this.lista, this.OnClickItemlistener);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adaptadorEstudiante);
        this.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editar)
                {
                    Actualizar(estudianteEditar.getID());
                    editar=false;
                    estudianteEditar=null;
                }
                else
                    {
                        Agregar();
                    }
                edtNombre.setText("");
                edtEdad.setText("");
            }
        });

        this.btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int edad=0;
                float Promedio;
                for (int i=0;i<lista.size();i++)
                {
                    edad=edad+lista.get(i).getEdad();
                }
                Promedio=edad/lista.size();
                Toast.makeText(MainActivity.this, "El Promedio de edad es : "+Promedio, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void CargarDatos() {
        this.dbEstudiantes=new PersistenciaEstudiantes(this);
        this.lista.clear();
        this.lista.addAll(0,this.dbEstudiantes.getAll());
        if(this.lista==null||this.lista.size()<0)
        {
            this.lista=new ArrayList<>();
        }

    }
    public void Agregar()
    {
        Estudiante estudiante=new Estudiante();
        estudiante.setNombre(this.edtNombre.getText().toString());
        estudiante.setEdad(Integer.parseInt(this.edtEdad.getText().toString()));
        if(dbEstudiantes.Insert(estudiante))
        {
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show();
            CargarDatos();
            this.adaptadorEstudiante.notifyDataSetChanged();
        }else
        {
            Toast.makeText(this, "Ha Ocurrido un error!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Actualizar(int ID)
    {
        Estudiante estudiante=new Estudiante();
        estudiante.setNombre(this.edtNombre.getText().toString());
        estudiante.setID(ID);
        estudiante.setEdad(Integer.parseInt(this.edtEdad.getText().toString()));

        if(dbEstudiantes.Update(estudiante))
        {
            Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_SHORT).show();
            CargarDatos();
            this.adaptadorEstudiante.notifyDataSetChanged();
        }else
        {
            Toast.makeText(this, "Ha Ocurrido un error!!", Toast.LENGTH_SHORT).show();
        }
    }
}
