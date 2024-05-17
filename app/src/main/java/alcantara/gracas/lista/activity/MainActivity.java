package alcantara.gracas.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.provider.ContactsContract;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import alcantara.gracas.lista.R;
import alcantara.gracas.lista.adapter.MyAdapter;
import alcantara.gracas.lista.model.MainActivityViewModel;
import alcantara.gracas.lista.model.MyItem;
import alcantara.gracas.lista.util.Util;

public class MainActivity extends AppCompatActivity {

    //Identificador de chamada
    static int NEW_ITEM_REQUEST = 1;


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
                startActivityForResult(i, NEW_ITEM_REQUEST);//executamos o Intent usando um metodo especial que a Activity destino ira retornar com dados em algum momento para a Activity que iniciou a navegacao

            }

        });

        RecyclerView rvItens = findViewById(R.id.rvItens);//obtemos o RecycleView

        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);//o MainActivityModel e quem guarda a lista agora
        List<MyItem> itens = vm.getItens();

        myAdapter = new MyAdapter(this,itens);
        rvItens.setAdapter(myAdapter);//e criado o MyAdapter e ele e setado no RecycleView

        rvItens.setHasFixedSize(true);//o metodo setHasFixedSize indica ao RecycleView que nao ha variacao de tamanho entre os itens da lista. Isso faz com que a construcao da lista seja mais rapida

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);//cria um decorador para a lista, que conciste apenas em uma linha separando cada lista
        rvItens.addItemDecoration(dividerItemDecoration);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ITEM_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {//verifica se as condicoes de retorno foram atendidas

                MyItem myItem = new MyItem();//cria um objeto de Myitem para guardar os dados do item

                //obtem os dados retornados por NewItemActivity e os gyarda dentro de MyItem
                myItem.title = data.getStringExtra("title");
                myItem.desc = data.getStringExtra("description");
                Uri selectPhotoUri = data.getData();

                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
                List<MyItem> itens = vm.getItens();

                itens.add(myItem);
                    myAdapter.notifyItemInserted(itens.size()-1);

                //Encontra o erro, porem nao para a aplicacao. O erro so e mostrado no logcat, assim a app pode continuar normalmente
                //o seguinte codigo esta dentro de um try-catch, onde a excecao e disparada caso o arquivo de imagem nao seja encontrado
                try {
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectPhotoUri, 100, 100);//carrega a funcao e a guarda dentro de um bitmap. Assim, criamos uma copia da imagem e nao precisamos usar o endereco da imagem
                    myItem.photo = photo;//guardamos o Bitmap da imagem dentro de um objeto do tipo MyItem.
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                itens.add(myItem);//adiciona o item a uma lista de itens que eh repassada para o Adapter
                myAdapter.notifyItemInserted(itens.size()-1);//modifica o Adapter para que o RecycleView se atualize e exiba o novo item
            }
        }




    }
}