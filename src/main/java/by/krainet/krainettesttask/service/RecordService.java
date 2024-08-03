package by.krainet.krainettesttask.service;

import by.krainet.krainettesttask.domain.Record;
import by.krainet.krainettesttask.domain.User;
import by.krainet.krainettesttask.exception.AfterDateException;
import by.krainet.krainettesttask.exception.EntityNotFoundException;
import by.krainet.krainettesttask.repository.RecordRepository;
import by.krainet.krainettesttask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    public Record save(Record record) {
        if (!record.getEndTime().isAfter(record.getStartTime())) {
            throw new AfterDateException("End time must be after start time");
        }

        return recordRepository.save(record);
    }

    public List<Record> findAllByUser(User user, int page, int size) {
        return recordRepository.findAllByUser(user, PageRequest.of(page, size))
                .stream()
                .toList();
    }

    public List<Record> findAllByUser(String username, int page, int size) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(User.class, Map.of("Username", username)));

        return recordRepository.findAllByUser(user, PageRequest.of(page, size))
                .stream()
                .toList();
    }

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public void deleteById(Long id) {
        recordRepository.deleteById(id);
    }
}
