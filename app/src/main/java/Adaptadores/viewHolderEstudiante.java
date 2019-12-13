package Adaptadores;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examah16i04001.R;

import ListenerItem.ListenerClick;

public class viewHolderEstudiante extends RecyclerView.ViewHolder {
    private TextView txbNombre;
    private TextView txbEdad;

    public viewHolderEstudiante(@NonNull View itemView, ListenerClick onClick) {
        super(itemView);
        this.txbNombre=itemView.findViewById(R.id.txbNombre);
        this.txbEdad=itemView.findViewById(R.id.txbEdad);
        itemView.setOnClickListener(onClick);
    }

    public TextView getTxbNombre() {
        return txbNombre;
    }

    public TextView getTxbEdad() {
        return txbEdad;
    }
}
