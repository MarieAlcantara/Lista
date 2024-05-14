package alcantara.gracas.lista.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import alcantara.gracas.lista.R;

public class NewItemActivity extends AppCompatActivity {

    //identificador de chamada
    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;//guarda o endereco da foto que esta em outro lugar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        ImageButton imgCl = findViewById(R.id.imbCl);//obtemos o botao
        imgCl.setOnClickListener(new View.OnClickListener() {//ouvidor de cliques
            @Override
            public void onClick(View v) {//
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);//abre a galeria
                photoPickerIntent.setType("image/*");//procura apenas imagens
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);//seleciona a imagem
            }
        });

        Button btnAddItem = findViewById(R.id.btnAddItem);//botao que adiciona item

        btnAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (photoSelected == null) {//Verifica se nenhuma imagem foi selecionada, se nao foi aparecera uma mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);//obtem o editText com o titulo
                String title = etTitle.getText().toString();//converte o texto do titulo em string
                if (title.isEmpty()) {//verifica se o titulo esta vazio, se estiver manda uma mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String desc = etDesc.getText().toString();


                if (desc.isEmpty()){//verifica se o campo da descricao esta vazio, se estiver manda uma mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição!", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();//cria uma intent para guardar os dados a serem retornados a MainActivity

                i.setData(photoSelected);//seta o Uri da imagem elecionada dentro do Intent
                i.putExtra("title", title);//seta o titulo dentro da Intent
                i.putExtra("description", desc);//seta a descricao dentro da Intent
                setResult(Activity.RESULT_OK, i);
                finish();//finaliza a activity
            }

        });


    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//retorna dados da activity que iniciou a acao
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST) {//verificamos se requestCode e referente ao fornecido na chamada do startActiviyForResult com id PHOTO_PICKER_REQUEST
            if(resultCode == Activity.RESULT_OK) {//verificamos se resultCode e um codigo de sucesso
                //Se as duas condicoes forem verdadeiras, conseguimos o resultado das proximas linhas
                photoSelected = data.getData();
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                imvfotoPreview.setImageURI(photoSelected);
            }
    }
}}


