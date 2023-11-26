package com.etpa.energy.management.command.repository;

import com.etpa.energy.management.entity.Profile;
import java.util.List;
import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

    List<ProfileHolder> findAllByProfileIn(Set<String> profileList);
}
