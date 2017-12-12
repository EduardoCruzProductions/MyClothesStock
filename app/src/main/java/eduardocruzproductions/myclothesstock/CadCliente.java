package eduardocruzproductions.myclothesstock;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import eduardocruzproductions.myclothesstock.entidades.Cliente;
import eduardocruzproductions.myclothesstock.util.ValidadorCPF;

public class CadCliente extends AppCompatActivity {

    EditText nome;
    EditText cpf;
    EditText data_nascimento;
    EditText telefone;
    EditText email;

    private Cliente cliente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nome = (EditText) findViewById(R.id.cadCliente_editText_nome);
        cpf = (EditText) findViewById(R.id.cadCliente_editText_cpf);
        data_nascimento = (EditText) findViewById(R.id.cadCliente_editText_dataNascimento);
        telefone = (EditText) findViewById(R.id.cadCliente_editText_telefone);
        email = (EditText) findViewById(R.id.cadCliente_editText_email);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void adicionarCliente(View view){

        String nome = this.nome.getText().toString();
        String cpf = this.cpf.getText().toString();
        String data_nascimento = this.data_nascimento.getText().toString();
        String telefone = this.telefone.getText().toString();
        String email = this.email.getText().toString();

        if(nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()){

            Toast.makeText(getApplicationContext(), R.string.cadCliente_error_preencherRequisitados, Toast.LENGTH_LONG).show();

        }else{

            if(ValidadorCPF.isCPF(cpf)){

                boolean proceed = true;
                Date data = new Date();

                if(!data_nascimento.isEmpty()){

                    try{

                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        data =  formato.parse(data_nascimento);

                    }catch(Exception e){

                        proceed = false;
                        Toast.makeText(getApplicationContext(), R.string.cadCliente_error_invalidDateFormat, Toast.LENGTH_LONG).show();

                    }

                }

                if(proceed){

                    try{

                        cliente = new Cliente();
                        cliente.setNome(nome);
                        cliente.setCpf(cpf);
                        cliente.setData_nascimento(data);
                        cliente.setTelefone(telefone);
                        cliente.setEmail(email);

                        cliente.save();

                        finish();

                    }catch (Exception e){

                        Toast.makeText(getApplicationContext(), R.string.error_falha_registro, Toast.LENGTH_LONG).show();

                    }

                }


            }else{

                Toast.makeText(getApplicationContext(), R.string.cadCliente_error_invalidCPF, Toast.LENGTH_LONG).show();

            }

        }

    }

}
