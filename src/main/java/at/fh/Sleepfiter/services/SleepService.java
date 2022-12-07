package at.fh.Sleepfiter.services;

import at.fh.Sleepfiter.entities.Sleep;
import at.fh.Sleepfiter.repositories.SleepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SleepService {
    @Autowired
    private SleepRepository sleepRepository;

    public ArrayList<Sleep> getAllData() {
        return (ArrayList<Sleep>) sleepRepository.findAll();
    }

    public List<String> getAllNames() {
        return sleepRepository.findPatientNames();
    }

    public ArrayList<Sleep> findAllByPatientName(String patientName) {
        return (ArrayList<Sleep>) sleepRepository.findAllByPatientName(patientName);
    }

    public void save(Sleep sleep) {
        sleepRepository.save(sleep);
    }
}
