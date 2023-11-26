package com.etpa.energy.management.command.repository;

import com.etpa.energy.management.entity.Meter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends MongoRepository<Meter,String> {

}
