package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.persistency.document.Secret;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretRepository extends MongoRepository<Secret, String> {

    @Query("{ $or: [ { name: { $regex: ?0, $options: 'i' } }, { description: { $regex: ?0, $options: 'i' } }, { link: { $regex: ?0, $options: 'i' } }, { username: { $regex: ?0, $options: 'i' } } ] }")
    Page<Secret> getAll(String search, Pageable pageable);
}
