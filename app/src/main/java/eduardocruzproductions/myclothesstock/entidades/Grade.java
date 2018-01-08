package eduardocruzproductions.myclothesstock.entidades;

import com.orm.SugarRecord;

public class Grade extends SugarRecord{

    private String tamanho;
    private int quantidade;
    private Produto produto;

    public Grade() {
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public boolean equals(Grade object){

        if(object.getId() == getId() &&
                object.getTamanho().equals(tamanho) &&
                object.getQuantidade() == quantidade &&
                object.getProduto().equals(produto)){

            return true;

        }else{

            return false;

        }

    }

}
