package ro.nicolaemariusghergu.queryit.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.Promotions;

import java.util.List;

@Repository
public interface PromotionsRepository extends JpaRepository<Promotions, Long> {

    @Override
    List<Promotions> findAll();
}
