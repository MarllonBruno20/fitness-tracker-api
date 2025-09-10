package br.com.marllonbruno.fitnesstracker.api.repository;

import br.com.marllonbruno.fitnesstracker.api.entity.DiaryEntry;
import br.com.marllonbruno.fitnesstracker.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {

    List<DiaryEntry> findByUserAndConsumptionTimestampBetween(User user, LocalDateTime start, LocalDateTime end);

}
