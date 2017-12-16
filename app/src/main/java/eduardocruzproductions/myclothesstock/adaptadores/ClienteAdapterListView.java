package eduardocruzproductions.myclothesstock.adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eduardocruzproductions.myclothesstock.R;
import eduardocruzproductions.myclothesstock.entidades.Cliente;
import eduardocruzproductions.myclothesstock.util.ValidadorCPF;

public class ClienteAdapterListView extends BaseAdapter{

    private ArrayList<Cliente> itens = new ArrayList<>();
    private LayoutInflater mInflater;

    public ClienteAdapterListView(Context context, List<Cliente> list) {

        for(Cliente c : list){
            itens.add(c);
        }

        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Cliente getItem(int i) {
        return itens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemSuporte itemHolder;

        if(view == null){

            view = mInflater.inflate(R.layout.adapter_list_cliente, null);

            itemHolder = new ItemSuporte();
            itemHolder.nome = (TextView) view.findViewById(R.id.adapter_list_cliente_textView_nome);
            itemHolder.cpf = (TextView) view.findViewById(R.id.adapter_list_cliente_textView_cpf);

            view.setTag(itemHolder);

        }else{

            itemHolder = (ItemSuporte) view.getTag();

        }

        Cliente item = itens.get(i);
        itemHolder.nome.setText(item.getNome());
        itemHolder.cpf.setText(ValidadorCPF.imprimeCPF(item.getCpf()));

        return view;

    }

    private class ItemSuporte{

        TextView nome;
        TextView cpf;

    }

}
