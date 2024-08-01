package by.krainet.krainettesttask.service;

import by.krainet.krainettesttask.domain.Record;
import by.krainet.krainettesttask.domain.User;
import by.krainet.krainettesttask.exception.AfterDateException;
import by.krainet.krainettesttask.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

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
}
