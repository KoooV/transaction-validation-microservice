package org.example.transactionvalidationmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlackListKey {
    private UUID accountId;
    private UUID clientId;

    public String getKey(){
        return accountId.toString() + "_"+ clientId.toString();
    }


}
