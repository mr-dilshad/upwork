package com.example.Python1.repository;

import com.example.Python1.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CassandraRepository<User, String> {
}
