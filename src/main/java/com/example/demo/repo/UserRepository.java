package com.example.demo.repo;

import com.example.demo.entity.MailUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MailUser,Long> {
}
