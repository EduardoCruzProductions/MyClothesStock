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
import eduardocruzproductions.myclothesstock.entidades.Produto;

public class ProdutoAdapterListView extends BaseAdapter{

    private ArrayList<Produto> itens = new ArrayList<>();
    private LayoutInflater mInflater;

    public ProdutoAdapterListView(Context context, List<Produto> list) {

        for(Produto p : list){

            itens.add(p);

        }

        mInflater = LayoutInflater.from(context);

    }

    public void updateItens(List<Produto> newList){

        itens.clear();

        for(Produto p : newList){

            itens.add(p);

        }

    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Produto getItem(int i) {
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

            view = mInflater.inflate(R.layout.adapter_list_produto, null);

            itemHolder = new ItemSuporte();
            itemHolder.referencia = (TextView) view.findViewById(R.id.adapter_list_produto_textView_referencia);
            itemHolder.marca = (TextView) view.findViewById(R.id.adapter_list_produto_textView_marca);
            itemHolder.descricao = (TextView) view.findViewById(R.id.adapter_list_produto_textView_descricao);
            itemHolder.precoCusto = (TextView) view.findViewById(R.id.adapter_list_produto_textView_precoCusto);
            itemHolder.precoVenda = (TextView) view.findViewById(R.id.adapter_list_produto_textView_precoVenda);

            view.setTag(itemHolder);

        }else{

            itemHolder = (ItemSuporte) view.getTag();

        }

        Produto item = itens.get(i);
        itemHolder.referencia.setText(item.getReferencia());
        itemHolder.marca.setText(item.getMarca());
        itemHolder.descricao.setText(item.getDescricao());

        DecimalFormat df = new DecimalFormat("#,##0.00");
        itemHolder.precoCusto.setText(df.format(item.getPreco_custo()));
        itemHolder.precoVenda.setText(df.format(item.getPreco_venda()));

        return view;

    }

    private class ItemSuporte{

        TextView referencia;
        TextView marca;
        TextView descricao;
        TextView precoCusto;
        TextView precoVenda;

    }

}
