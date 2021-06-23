package com.example.ex4.Repository;

        import org.springframework.data.jpa.repository.JpaRepository;


        import java.util.List;

/* default scope of this Bean is "singleton" */
public interface MessagesRepository extends JpaRepository<Messages, Long> {
     List<Messages> findFirst5ByOrderByIdDesc();
     Messages findFirst1ByOrderByIdDesc();
     List<Messages> findAllByUserName(String NameOfUser);
     List<Messages> findAllByMessageContains(String message);

}