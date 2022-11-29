package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.nicolaemariusghergu.queryit.model.Promotion;

import java.util.List;

@Repository
@Transactional
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    List<Promotion> findAllByName(String name);
}
