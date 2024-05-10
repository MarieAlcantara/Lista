package alcantara.gracas.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alcantara.gracas.lista.R;
import alcantara.gracas.lista.activity.MainActivity;
import alcantara.gracas.lista.model.MyItem;
//um adapter para cada lista
public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//cria os elementos de interface
        LayoutInflater inflater = LayoutInflater.from(mainActivity);//Esse inflador sera usado para ler o arquivo xml de layout do item e entao criar os elementos de interface propriamente ditos
        View v = inflater.inflate(R.layout.item_list,parent,false);//usamos o inflador para criar os elementos de interface referentes a um item e os guardamos dentro de um objeto do tipo View
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {//recebe a interface que foi recebida no metodo anterior

        MyItem myItem = itens.get(position);

        View v = holder.itemView;

        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageURI(myItem.foto);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myItem.desc);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }



}
