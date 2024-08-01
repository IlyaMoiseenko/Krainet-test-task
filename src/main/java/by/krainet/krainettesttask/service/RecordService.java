package by.krainet.krainettesttask.service;

import by.krainet.krainettesttask.domain.Record;
import by.krainet.krainettesttask.exception.AfterDateException;
import by.krainet.krainettesttask.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
