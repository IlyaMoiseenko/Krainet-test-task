package by.krainet.krainettesttask.repository;

import by.krainet.krainettesttask.domain.Record;
import by.krainet.krainettesttask.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    Page<Record> findAllByUser(User user, Pageable pageable);
}
