package ro.nicolaemariusghergu.queryit.service;

import org.springframework.lang.NonNull;
import ro.nicolaemariusghergu.queryit.model.Minishop;
import ro.nicolaemariusghergu.queryit.model.data.MinishopCategory;
import ro.nicolaemariusghergu.queryit.model.data.Product;

import java.util.List;
import java.util.Optional;

public interface MinishopService {

    Optional<Minishop> findById(@NonNull Long id);

    List<Minishop> findAllByMinishopCategory(MinishopCategory minishopCategory);

    List<Minishop> findAllByProduct(Product product);
}
