package com.example.Python1.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"userId"})
@Table("user")
@Builder
public class User {

    @PrimaryKey
    @Column("userId")
    private String userId = UUID.randomUUID().toString();;

    @Column("userName")
    private String userName;

    @Column("userEmail")
    private String userEmail;

}
