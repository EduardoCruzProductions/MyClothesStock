package eduardocruzproductions.myclothesstock;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import eduardocruzproductions.myclothesstock.entidades.Grade;

public class InsertGrade extends AppCompatActivity {

    private ArrayList<Grade> listGrade = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_grade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listGrade = CadProduto.getListGrade();

        for(Grade g : listGrade){

            System.out.println(g.getQuantidade()+" - "+g.getTamanho());

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //metodo chanado ai finalizar a activity
    @Override
    public void finish(){
        super.finish();
        CadProduto.setListGrade(listGrade);
    }

    //metodo chamado quando a opção voltar da actionbar é selecionado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
