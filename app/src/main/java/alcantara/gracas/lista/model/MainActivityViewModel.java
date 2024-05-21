package alcantara.gracas.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

//Guarda a lista de itens cadastrados
public class MainActivityViewModel extends ViewModel {
    List<MyItem> itens = new ArrayList<>();
    public List<MyItem> getItens() {
        return itens;
    }
}
