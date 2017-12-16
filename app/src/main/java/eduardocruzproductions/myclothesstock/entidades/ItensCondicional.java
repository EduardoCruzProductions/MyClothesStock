package eduardocruzproductions.myclothesstock.entidades;


import com.orm.SugarRecord;

public class ItensCondicional extends SugarRecord{

    private Condicional condicional;
    private Grade grade;
    private int quantidade;
    private Double valor;

    public ItensCondicional() {
    }

    public Condicional getCondicional() {
        return condicional;
    }

    public void setCondicional(Condicional condicional) {
        this.condicional = condicional;
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
}
