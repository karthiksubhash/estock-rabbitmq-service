package com.messagequeueservices.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.messagequeueservices.model.EstockInfoModel;

public interface EstockInfoRepository extends MongoRepository<EstockInfoModel, Long> {

}
