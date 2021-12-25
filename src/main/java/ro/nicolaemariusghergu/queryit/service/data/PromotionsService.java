package ro.nicolaemariusghergu.queryit.service.data;

import lombok.NonNull;
import ro.nicolaemariusghergu.queryit.model.Promotions;

import java.util.List;

public interface PromotionsService {

    @NonNull List<Promotions> findAll();
}
