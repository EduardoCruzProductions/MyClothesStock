package eduardocruzproductions.myclothesstock.entidades;


import com.orm.SugarContext;
import com.orm.SugarRecord;

public class ItensVenda extends SugarRecord{

    private Venda venda;
    private Grade grade;
    private int quantidade;
    private Double valor;
    private Double valor_real;

    public ItensVenda() {
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor_real() {
        return valor_real;
    }

    public void setValor_real(Double valor_real) {
        this.valor_real = valor_real;
    }

    public boolean equals(ItensVenda object){

        if(object.getId() == getId() &&
                object.getQuantidade() == quantidade &&
                object.getValor() == valor &&
                object.getValor_real() == valor_real &&
                object.getGrade().equals(grade) &&
                object.getVenda().equals(venda)){

            return true;

        }else{

            return false;

        }

    }

    /**
     * Compara objetos que ainda nao foram registrados. <br/>
     * Ou seja, objetos que não possuem ID e referencias de banco de dados.
     * @param object Objeto a ser comparado.
     */
    public boolean equalsUnregistered(ItensVenda object){

        if(object.getQuantidade() == quantidade &&
                object.getValor() == valor &&
                object.getValor_real() == valor_real &&
                object.getGrade().equals(grade)){

            return true;

        }else{

            return false;

        }

    }

}
