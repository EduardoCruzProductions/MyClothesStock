package eduardocruzproductions.myclothesstock.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eduardocruzproductions.myclothesstock.R;
import eduardocruzproductions.myclothesstock.entidades.Grade;

public class GradeAdapterListView extends BaseAdapter{

    private ArrayList<Grade> itens = new ArrayList<>();
    private LayoutInflater mInflater;


    public GradeAdapterListView(Context context, ArrayList<Grade> list) {

        for(Grade g : list){
            itens.add(g);
        }

        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Grade getItem(int i) {
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

            view = mInflater.inflate(R.layout.adapter_list_grade, null);

            itemHolder = new ItemSuporte();
            itemHolder.tamanho = ((TextView) view.findViewById(R.id.adapter_list_grade_textView_tamanho));
            itemHolder.quantidade = ((TextView) view.findViewById(R.id.adapter_list_grade_textView_quantidade));
            view.setTag(itemHolder);

        }else{

            itemHolder = (ItemSuporte) view.getTag();

        }

        Grade item = itens.get(i);
        itemHolder.tamanho.setText(item.getTamanho());
        itemHolder.quantidade.setText(Integer.toString(item.getQuantidade()));

        return view;
    }

    private class ItemSuporte{

        TextView tamanho;
        TextView quantidade;

    }

}
