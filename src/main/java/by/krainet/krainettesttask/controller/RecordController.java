package by.krainet.krainettesttask.controller;

import by.krainet.krainettesttask.domain.Record;
import by.krainet.krainettesttask.domain.User;
import by.krainet.krainettesttask.dto.request.RecordRequest;
import by.krainet.krainettesttask.dto.response.PageResponse;
import by.krainet.krainettesttask.dto.response.RecordResponse;
import by.krainet.krainettesttask.mapper.RecordMapper;
import by.krainet.krainettesttask.service.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<PageResponse<RecordResponse>> getAllByAuthenticatedUser(Authentication authentication,
                                                     @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                     @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        User user = (User) authentication.getPrincipal();
        List<RecordResponse> response = recordService.findAllByUser(user, page, size)
                .stream()
                .map(recordMapper::toResponse)
                .toList();


        return new ResponseEntity<>(
                new PageResponse<>(
                        response,
                        page,
                        size,
                        response.size()
                ),
                HttpStatus.FOUND
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<PageResponse<RecordResponse>> getAllByUser(@RequestParam("username") String username,
                                                                     @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                     @RequestParam(name = "size", required = false, defaultValue = "10") int size)
    {
        List<RecordResponse> response = recordService.findAllByUser(username, page, size)
                .stream()
                .map(recordMapper::toResponse)
                .toList();

        return new ResponseEntity<>(
                new PageResponse<>(
                        response,
                        page,
                        size,
                        response.size()
                ),
                HttpStatus.FOUND
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<PageResponse<RecordResponse>> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                               @RequestParam(name = "size", required = false, defaultValue = "10") int size)
    {
        List<RecordResponse> response = recordService.findAll()
                .stream()
                .map(recordMapper::toResponse)
                .toList();

        return new ResponseEntity<>(
                new PageResponse<>(
                        response,
                        page,
                        size,
                        response.size()
                ),
                HttpStatus.FOUND
        );
    }
}
