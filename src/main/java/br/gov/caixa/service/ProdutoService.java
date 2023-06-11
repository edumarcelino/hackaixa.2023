package br.gov.caixa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import br.gov.caixa.model.ProdutoModel;
import br.gov.caixa.repository.ProdutoRepository;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    List<ProdutoModel> retornaProdutosEmprestimo(Integer prazo, BigDecimal valorDesejado) {

        // Busco os produtos na base para iterar e obter os produtos que atendam os
        // criterios da simulacao
        List<ProdutoModel> listProdutos = produtoRepository.listAllOrderedByName();

        // Crio o list de Retorno dos produtos validos para retorno
        List<ProdutoModel> listProdutoRetorno = new ArrayList<ProdutoModel>();

        for (ProdutoModel produto : listProdutos) {
            if (valorDesejado.compareTo(produto.getVrMinimo()) >= 0) {
                if (produto.getVrMaximo() == null
                        || valorDesejado.compareTo(produto.getVrMaximo()) <= 0) {
                    if (prazo.compareTo(produto.getNuMinimoMeses()) >= 0) {
                        if (produto.getNuMaximoMeses() == null
                                || prazo.compareTo(produto.getNuMaximoMeses()) <= 0) {
                            // Apos validar o produto, insiro na lista de produtos validos para o
                            // emprestimo.
                            listProdutoRetorno.add(produto);
                        }
                    }
                }
            }
        }
        return listProdutoRetorno;
    }
}
