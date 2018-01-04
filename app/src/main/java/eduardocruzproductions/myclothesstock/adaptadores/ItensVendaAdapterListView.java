package eduardocruzproductions.myclothesstock.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import eduardocruzproductions.myclothesstock.R;
import eduardocruzproductions.myclothesstock.entidades.ItensVenda;

public class ItensVendaAdapterListView extends BaseAdapter{

    private ArrayList<ItensVenda> itens = new ArrayList<>();
    private LayoutInflater mInflater;

    public ItensVendaAdapterListView(Context context, List<ItensVenda> list) {

        for(ItensVenda iVend : list){

            itens.add(iVend);

        }

        mInflater = LayoutInflater.from(context);

    }

    public void updateItens(List<ItensVenda> newList){

        itens.clear();

        for(ItensVenda iVend : newList){

            itens.add(iVend);

        }

    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public ItensVenda getItem(int i) {
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

            view = mInflater.inflate(R.layout.adapter_list_itens_venda, null);

            itemHolder = new ItemSuporte();
            itemHolder.referencia = (TextView) view.findViewById(R.id.adapter_list_itens_venda_textView_referencia);
            itemHolder.marca = (TextView) view.findViewById(R.id.adapter_list_itens_venda_textView_marca);
            itemHolder.tamanho = (TextView) view.findViewById(R.id.adapter_list_itens_venda_textView_tamanho);
            itemHolder.quantidade = (TextView) view.findViewById(R.id.adapter_list_itens_venda_textView_quantidade);
            itemHolder.preco = (TextView) view.findViewById(R.id.adapter_list_itens_venda_textView_preco);
            itemHolder.precoReal = (TextView) view.findViewById(R.id.adapter_list_itens_venda_textView_precoReal);

            view.setTag(itemHolder);

        }else{

            itemHolder = (ItemSuporte) view.getTag();

        }

        ItensVenda item = itens.get(i);
        itemHolder.referencia.setText(item.getGrade().getProduto().getReferencia());
        itemHolder.marca.setText(item.getGrade().getProduto().getMarca());
        itemHolder.tamanho.setText(item.getGrade().getTamanho());
        itemHolder.quantidade.setText(String.valueOf(item.getQuantidade()));

        DecimalFormat df = new DecimalFormat("#,##0.00");
        itemHolder.preco.setText(df.format(item.getValor()));
        itemHolder.precoReal.setText(df.format(item.getValor_real()));

        return view;

    }

    private class ItemSuporte{

        TextView referencia;
        TextView marca;
        TextView tamanho;
        TextView quantidade;
        TextView preco;
        TextView precoReal;

    }

}
