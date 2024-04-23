package alcantara.gracas.lista.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import alcantara.gracas.lista.R;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fAbAddItem = findViewById(R.layout.activity_main);//obtemos o botao
        fAbAddItem.setOnClickListener(new View.OnClickListener() {//registramos um ouvidor de cliques
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);//intent para navegar para outra tela
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });
    }
}