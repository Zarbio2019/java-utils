package com.appsdeveloperblog.tutorials.junit.io;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<UserEntity, Long> {
    // these are Query methods:
    // Spring Framework derived by the names of the method to build a query for database.
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);
    UserEntity findByEmailEndsWith(String email);

    // This is a JPQL Query:
    // for complex queries, you build the SQL query.
    // the method name can be any.
    // this query find all emails that ends with emailDomain (e.g.: @gmail).
    @Query("select user from UserEntity user where user.email like %:emailDomain")
    List<UserEntity> findUsersWithEmailEndingWith(@Param("emailDomain") String emailDomain);
}