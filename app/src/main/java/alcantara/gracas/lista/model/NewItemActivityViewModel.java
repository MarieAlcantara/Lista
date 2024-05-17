package alcantara.gracas.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

//Garante que a imagem continue aparecendo na tela mesmo que ele gire a tela
public class NewItemActivityViewModel extends ViewModel {
    Uri  selectPhotoLocation = null;//guarda o endereco da imagem escolhida pelo usuario

    //obtem a lista de itens
    public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
    }

    //seta o endereco Uri dentro do ViewModel
    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }

}
