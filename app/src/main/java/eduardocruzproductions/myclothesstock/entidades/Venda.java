package eduardocruzproductions.myclothesstock.entidades;

import com.orm.SugarRecord;

import java.util.Date;

public class Venda extends SugarRecord{

    private Cliente cliente;
    private Date data;
    private Double valor_total;
    private Double valor_real;
    private String status;

    public Venda() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public Double getValor_real() {
        return valor_real;
    }

    public void setValor_real(Double valor_real) {
        this.valor_real = valor_real;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
