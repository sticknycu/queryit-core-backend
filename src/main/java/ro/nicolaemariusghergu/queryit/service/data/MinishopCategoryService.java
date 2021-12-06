package ro.nicolaemariusghergu.queryit.service.data;

import ro.nicolaemariusghergu.queryit.model.data.MinishopCategory;

import java.util.List;
import java.util.Optional;

public interface MinishopCategoryService {

    MinishopCategory getById(Long id);

    Optional<MinishopCategory> getByName(String name);

    List<MinishopCategory> findAll();
}
