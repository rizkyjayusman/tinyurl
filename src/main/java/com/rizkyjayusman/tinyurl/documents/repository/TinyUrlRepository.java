package com.rizkyjayusman.tinyurl.documents.repository;

import com.rizkyjayusman.tinyurl.documents.TinyUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TinyUrlRepository extends MongoRepository<TinyUrl, Long> {
    Optional<TinyUrl> findByUniqueCode(String uniqueCode);
}
