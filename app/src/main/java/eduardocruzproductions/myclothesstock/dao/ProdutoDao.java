package eduardocruzproductions.myclothesstock.dao;

import java.util.ArrayList;
import java.util.List;

import eduardocruzproductions.myclothesstock.entidades.Grade;
import eduardocruzproductions.myclothesstock.entidades.Produto;

public class ProdutoDao {

    public static List<Produto> listAll(){

        return Produto.listAll(Produto.class);

    }

    public static List<Produto> listProdutoVendivel(){

        List<Produto> finalList = new ArrayList<>();

        for(Grade g : Grade.listAll(Grade.class)){

            if(g.getQuantidade() != 0){

                boolean can = true;

                for(Produto p : finalList){

                    if(p.getId() == g.getProduto().getId()){

                        can = false;
                        break;

                    }

                }

                if(can){

                    finalList.add(g.getProduto());

                }

            }

        }


        return finalList;

    }


    /**
     * Remove os itens que não possuem estoque <br/>
     * Metodo usa de muito processamento.
     * @param list Representa a lista inteira de produtos.
     * @return Uma lista com os itens que nao possuem estoque removidos.
     * @deprecated
     */
    public static List<Produto> removeNoStockUnit(List<Produto> list){

        List<Produto> newList = new ArrayList<>();

        for(Produto produto : list){

            List<Grade> listGrade = Grade.find(Grade.class,"PRODUTO = ?", produto.getId().toString());

            int somatoria = 0;

            for(Grade grade : listGrade){
                somatoria+= grade.getQuantidade();
            }

            if(somatoria != 0){

                newList.add(produto);

            }

        }

        return newList;

    }

}
