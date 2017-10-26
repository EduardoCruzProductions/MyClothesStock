package eduardocruzproductions.myclothesstock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class CadProduto extends AppCompatActivity {

    EditText referencia;
    EditText marca;
    EditText descricao;
    EditText precoCusto;
    EditText precoVenda;
    TextView totalItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);

        referencia = (EditText) findViewById(R.id.cadProduto_alert_gerador_editText_valor);
        marca = (EditText) findViewById(R.id.cadProduto_editText_marca);
        descricao = (EditText) findViewById(R.id.cadProduto_editText_descricao);
        precoCusto = (EditText) findViewById(R.id.cadProduto_editText_precoCusto);
        precoVenda = (EditText) findViewById(R.id.cadProduto_editText_precoVenda);
        totalItens = (TextView) findViewById(R.id.cadProduto_textView_qtItens);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void generateVenda(View view){

        String pCusto = this.precoCusto.getText().toString();

        if(pCusto.isEmpty()){

            Toast.makeText(getApplicationContext(), R.string.cadProduto_error_null_abrirGerador, Toast.LENGTH_LONG).show();

        }else {

            try{

                Double value = Double.parseDouble(pCusto);

                LayoutInflater layoutInflater = LayoutInflater.from(CadProduto.this);
                View promptView = layoutInflater.inflate(R.layout.alert_cad_produto_gerador, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CadProduto.this);
                alertDialogBuilder.setView(promptView);

                final RadioButton rbValor = (RadioButton) findViewById(R.id.cadProduto_alert_gerador_radioButton_valor);
                final RadioButton rbPorcent = (RadioButton) findViewById(R.id.cadProduto_alert_gerador_radioButton_porcent);
                final EditText valor = (EditText) findViewById(R.id.cadProduto_alert_gerador_editText_valor);

                alertDialogBuilder.setCancelable(true)
                        .setPositiveButton(R.string.text_confirm, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //action positiva

                            }
                        })
                        .setNegativeButton(R.string.text_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        //caso o botao cancelar seja selecionado

                                        dialog.cancel();//cancelar operacao

                                    }
                                });

                AlertDialog alert = alertDialogBuilder.create();
                alert.show();

            }catch(Exception e){

                System.err.println("Error - "+e.getMessage());
                Toast.makeText(getApplicationContext(), R.string.error_conversao_valores, Toast.LENGTH_LONG).show();

            }

        }

    }

    public void adicionar(){

    }

}
