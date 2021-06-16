
package com.example.ex4.Repository;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;

        import java.util.List;

/* default scope of this Bean is "singleton" */
public interface MessagesRepository extends JpaRepository<Messages, Long> {
     List<Messages> findFirst5ByOrderByIdDesc();
     Messages findFirst1ByOrderByIdDesc();
     List<Messages> findAllByUserName(String NameOfUser);
     List<Messages> findAllByMessageContains(String message);

    /* add here the queries you may need - in addition to CRUD operations
    List<User> findByUserName(String userName);
    List<User> findUserByUserName(String userName);
    List<User> findByEmail(String email);
    List<User> findByUserNameAndEmail(String userName, String email);
    List<User> findFirst10ByOrderByUserNameDesc(); */


}