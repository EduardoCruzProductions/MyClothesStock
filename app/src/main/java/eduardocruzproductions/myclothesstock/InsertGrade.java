package eduardocruzproductions.myclothesstock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import eduardocruzproductions.myclothesstock.adaptadores.GradeAdapterListView;
import eduardocruzproductions.myclothesstock.entidades.Grade;

public class InsertGrade extends AppCompatActivity {

    EditText editTextTamanho;
    EditText editTextQuantidade;

    ListView listView;

    private ArrayList<Grade> listGrade = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_grade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listGrade = CadProduto.getListGrade();

        editTextTamanho = (EditText) findViewById(R.id.insertGrade_editText_tamanho);
        editTextQuantidade = (EditText) findViewById(R.id.insertGrade_editText_quantidade);

        listView = (ListView) findViewById(R.id.insertGrade_listView);

        updateList();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateList(){

        GradeAdapterListView adapter = new GradeAdapterListView(getApplicationContext(),listGrade);
        listView.setAdapter(adapter);

    }

    public void add(View v){

        String tamanho = editTextTamanho.getText().toString();
        String textQuantidade = editTextQuantidade.getText().toString();
        int quantidade;

        if(tamanho.isEmpty() || textQuantidade.isEmpty()){

            Toast.makeText(getApplicationContext(), R.string.error_preecha_todos_valores, Toast.LENGTH_LONG).show();

        }else{

            try{

                quantidade = Integer.parseInt(textQuantidade);
                tamanho = tamanho.toUpperCase();

                Grade g = new Grade();
                g.setTamanho(tamanho);
                g.setQuantidade(quantidade);
                listGrade.add(g);

                updateList();
                editTextTamanho.setText("");
                editTextQuantidade.setText("");

            }catch (Exception e){

                System.err.println("Erro de conversão de valores:");
                System.err.println(e.getMessage());

                Toast.makeText(getApplicationContext(), R.string.error_preecha_todos_valores, Toast.LENGTH_LONG).show();

            }

        }

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
