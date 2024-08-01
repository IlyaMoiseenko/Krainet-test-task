package by.krainet.krainettesttask.controller;

import by.krainet.krainettesttask.domain.Record;
import by.krainet.krainettesttask.domain.User;
import by.krainet.krainettesttask.dto.request.RecordRequest;
import by.krainet.krainettesttask.mapper.RecordMapper;
import by.krainet.krainettesttask.service.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final RecordMapper recordMapper;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<Record> createNewRecord(@RequestBody @Valid RecordRequest request,
                                                          Authentication authentication)
    {
        request.setUser((User) authentication.getPrincipal());
        Record record = recordMapper.toDomain(request);

        return new ResponseEntity<>(
                recordService.save(record),
                HttpStatus.CREATED
        );
    }
}
