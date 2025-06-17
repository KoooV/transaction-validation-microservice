package org.example.transactionvalidationmicroservice.repository;

import org.example.transactionvalidationmicroservice.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Repository
public interface BlackListRepository {
    public boolean checkClientBlackList(UUID accountId);
    public Map<String, BlackList.Status> checkActive();
    public long blockTime(long blockBeginTimestamp, long blockEndTimestamp);
    public boolean checkBlockIsActive(long blockBeginTimestamp, long blockEndTimestamp);
}
