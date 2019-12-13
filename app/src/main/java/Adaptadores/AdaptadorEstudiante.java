package Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examah16i04001.R;

import java.util.List;

import Entidades.Estudiante;
import ListenerItem.ListenerClick;

public class AdaptadorEstudiante extends RecyclerView.Adapter<viewHolderEstudiante> {
    List<Estudiante> source;
    ListenerClick OnClick;

    public  AdaptadorEstudiante (List<Estudiante> lista, ListenerClick onCLick)
    {
        this.source=lista;
        this.OnClick=onCLick;

    }
    @NonNull
    @Override
    public viewHolderEstudiante onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiante,parent,false);
        viewHolderEstudiante vh=new viewHolderEstudiante(v,this.OnClick);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderEstudiante holder, int position) {
        holder.itemView.setTag(position);
        holder.getTxbNombre().setText(this.source.get(position).getNombre());
        holder.getTxbEdad().setText(Integer.toString(this.source.get(position).getEdad()));

    }

    @Override
    public int getItemCount() {
        return this.source.size();
    }
}
