package org.example.transactionvalidationmicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlackList extends AbstractPersistable<Long> {


    public enum Status{
        OK,
        BLACKLIST
    }
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "clirnt_id", nullable = false)
    private UUID clientId;

    @Column(name = "block_begin_time", nullable = false)
    private long blockBeginTimestamp;

    @Column(name = "block_end_time", nullable = false)
    private long blockEndTimestamp;

    @Column(name = "reason", nullable = false)
    private String blockReason;

    @Column(name = "status", nullable = false)
    private Status status;
}
