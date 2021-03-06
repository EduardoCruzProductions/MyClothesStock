package eduardocruzproductions.myclothesstock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import eduardocruzproductions.myclothesstock.entidades.Grade;
import eduardocruzproductions.myclothesstock.entidades.Produto;

public class CadProduto extends AppCompatActivity {

    EditText referencia;
    EditText marca;
    EditText descricao;
    EditText precoCusto;
    EditText precoVenda;
    static TextView totalItens;

    private static ArrayList<Grade> listGrade = new ArrayList<>();

    private static Produto produto = null;

    public static ArrayList<Grade> getListGrade() {
        return listGrade;
    }

    public static void setListGrade(ArrayList<Grade> listGrade) {
        CadProduto.listGrade = listGrade;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);

        referencia = (EditText) findViewById(R.id.cadProduto_editText_referencia);
        marca = (EditText) findViewById(R.id.cadProduto_editText_marca);
        descricao = (EditText) findViewById(R.id.cadProduto_editText_descricao);
        precoCusto = (EditText) findViewById(R.id.cadProduto_editText_precoCusto);
        precoVenda = (EditText) findViewById(R.id.cadProduto_editText_precoVenda);
        totalItens = (TextView) findViewById(R.id.cadProduto_textView_qtItens);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateQtItensText();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public static void updateQtItensText(){

        Integer total = 0;
        for(Grade g : listGrade){

            total += g.getQuantidade();

        }

        totalItens.setText(total.toString());

    }

    public void generateVenda(View view){

        String pCusto = this.precoCusto.getText().toString();

        if(pCusto.isEmpty()){

            Toast.makeText(getApplicationContext(), R.string.cadProduto_error_null_abrirGerador, Toast.LENGTH_LONG).show();

        }else {

            LayoutInflater layoutInflater = LayoutInflater.from(CadProduto.this);
            View promptView = layoutInflater.inflate(R.layout.alert_cad_produto_gerador, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CadProduto.this);
            alertDialogBuilder.setView(promptView);

            final RadioGroup radios = ((RadioGroup) promptView.findViewById(R.id.venda_produto_alert_defineAtributs_radioGroup));
            final EditText valor = ((EditText) promptView.findViewById(R.id.cadProduto_editText_referencia));

            alertDialogBuilder.setCancelable(true)
                        .setPositiveButton(R.string.text_confirm, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                try{

                                    Double v = Double.parseDouble(valor.getText().toString());

                                    switch (radios.getCheckedRadioButtonId()){

                                        case R.id.venda_produto_alert_defineAtributs_radioButton_valor:

                                            calcPrecoVenda(v, "valor");
                                            break;

                                        case R.id.cadProduto_alert_gerador_radioButton_porcent:

                                            calcPrecoVenda(v, "porcentagem");
                                            break;

                                    }

                                }catch(Exception e){

                                    Toast.makeText(getApplicationContext(), R.string.error_conversao_valores, Toast.LENGTH_LONG).show();

                                }

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

        }

    }

    public void calcPrecoVenda(Double valor, String type) throws Exception{


        Double base = Double.parseDouble(this.precoCusto.getText().toString());

        if(type.equals("valor")){

            base += valor;

        }else if(type.equals("porcentagem")){

            base += base*(valor/100);

        }

        this.precoVenda.setText(base.toString());

    }

    public void showGradeIncluder(View view){

        Intent i = new Intent(CadProduto.this, InsertGrade.class);
        startActivity(i);
        updateQtItensText();

    }

    public void adicionar(View view){

        if(listGrade.size() == 0){

            Toast.makeText(getApplicationContext(), R.string.insertGrade_error_listaVazia, Toast.LENGTH_LONG).show();

        }else{

            String referencia = this.referencia.getText().toString();
            String marca = this.marca.getText().toString();
            String descricao = this.descricao.getText().toString();
            String precoCusto = this.precoCusto.getText().toString();
            String precoVenda = this.precoVenda.getText().toString();

            if(referencia.isEmpty() ||
                    marca.isEmpty() ||
                    descricao.isEmpty() ||
                    precoCusto.isEmpty() ||
                    precoVenda.isEmpty()){

                Toast.makeText(getApplicationContext(), R.string.error_preecha_todos_valores, Toast.LENGTH_LONG).show();


            }else{

                Double precoCustoD = 0.d;
                Double precoVendaD = 0.d;

                try{

                    precoCustoD = Double.parseDouble(precoCusto);
                    precoVendaD = Double.parseDouble(precoVenda);

                }catch (Exception e){

                    Toast.makeText(getApplicationContext(), R.string.error_conversao_valores, Toast.LENGTH_LONG).show();

                    precoCustoD = 0.d;
                    precoVendaD = 0.d;

                }

                if(!(precoCustoD.equals(0.d) || precoVendaD.equals(0.d))){

                    try{

                        produto = new Produto();
                        produto.setReferencia(referencia);
                        produto.setMarca(marca);
                        produto.setDescricao(descricao);
                        produto.setPreco_custo(precoCustoD);
                        produto.setPreco_venda(precoVendaD);

                        saveProcess();
                        clearAll();

                    }catch(Exception e){

                        Toast.makeText(getApplicationContext(), R.string.error_falha_registro, Toast.LENGTH_LONG).show();

                    }

                }

            }

        }

    }

    private void clearAll(){

        produto = null;
        listGrade = new ArrayList<>();

        clearScreen();

    }

    private void clearScreen(){

        descricao.setText("");
        marca.setText("");
        referencia.setText("");
        precoVenda.setText("");
        precoCusto.setText("");
        totalItens.setText("0");

    }

    private void saveProcess() throws Exception{

        produto.save();

        System.out.println("Produto: " +
                "\nId: "+produto.getId()+
                "\nDescricao: "+produto.getDescricao());

        System.out.println("Grade:" );
        for(Grade grade : listGrade){

            grade.setProduto(produto);
            grade.save();
            System.out.println(grade.getProduto().getId() + " - "
                    +grade.getTamanho()+" - "+grade.getQuantidade());

        }

    }

}
