package br.gov.caixa.repository;

import java.util.List;

import br.gov.caixa.model.ProdutoModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<ProdutoModel> {

    public List<ProdutoModel> listAllOrderedByName() {
        return this.listAll(Sort.by("noProduto").ascending());
    }

    
}