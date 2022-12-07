package at.fh.Sleepfiter.repositories;


import at.fh.Sleepfiter.entities.Sleep;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SleepRepository extends  CrudRepository<Sleep, Integer> {
    @Query("select distinct patientName from Sleep")
    List<String> findPatientNames();

    List<Sleep> findAllByPatientName(String patientName);
}
