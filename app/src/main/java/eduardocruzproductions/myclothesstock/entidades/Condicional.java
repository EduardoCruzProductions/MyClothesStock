package eduardocruzproductions.myclothesstock.entidades;


import com.orm.SugarRecord;

import java.util.Date;

public class Condicional extends SugarRecord{

    private Cliente cliente;
    private Date data_realizacao;
    private Date data_devolucao;
    private Double valor_total;

    public Condicional() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData_realizacao() {
        return data_realizacao;
    }

    public void setData_realizacao(Date data_realizacao) {
        this.data_realizacao = data_realizacao;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }
}
