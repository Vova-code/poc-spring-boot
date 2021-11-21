package com.vova.mongodbdemo.dao.sessions;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.session.web.http.HttpSessionIdResolver;

import java.util.Optional;

public interface SessionDao extends MongoRepository<HttpSessionIdResolver, String> {

    Optional<HttpSessionIdResolver> findById(String id);
}
