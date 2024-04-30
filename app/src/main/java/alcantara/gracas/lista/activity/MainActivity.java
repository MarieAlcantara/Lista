package alcantara.gracas.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import alcantara.gracas.lista.R;
import alcantara.gracas.lista.adapter.MyAdapter;
import alcantara.gracas.lista.model.MyItem;

public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fAbAddItem = findViewById(R.id.fabAddNewItem);//obtemos o botao
        fAbAddItem.setOnClickListener(new View.OnClickListener() {//registramos um ouvidor de cliques
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);//intent para navegar para outra tela
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }

        });

        RecyclerView rvItens = findViewById(R.id.rvItens);//obtemos o RecycleView

        myAdapter = new MyAdapter(this,itens);
        rvItens.setAdapter(myAdapter);//e criado o MyAdapter e ele e setado no RecycleView

        rvItens.setHasFixedSize(true);//o metodo setHasFixedSize indica ao RecycleView que nao ha variacao de tamanho entre os itens da lista. Isso faz com que a construcao da lista seja mais rapida

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            rvItens.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ITEM_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.desc = data.getStringExtra("description");
                myItem.photo = data.getData();

                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }




    }
}