package com.etpa.energy.management.query.repository;

import com.etpa.energy.management.entity.Meter;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends MongoRepository<Meter,String> {

  Optional<Meter> findByMeterId(String meterId);

}
