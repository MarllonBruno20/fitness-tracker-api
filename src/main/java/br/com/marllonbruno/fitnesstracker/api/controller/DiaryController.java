package br.com.marllonbruno.fitnesstracker.api.controller;

import br.com.marllonbruno.fitnesstracker.api.dto.request.DiaryEntryRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.DailySummaryResponse;
import br.com.marllonbruno.fitnesstracker.api.dto.response.DiaryEntryResponse;
import br.com.marllonbruno.fitnesstracker.api.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("")
    public ResponseEntity<DiaryEntryResponse> addEntry(@RequestBody DiaryEntryRequest request) {
        DiaryEntryResponse diaryEntryResponse = diaryService.addEntry(request);
        return ResponseEntity.ok(diaryEntryResponse);
    }

    @GetMapping("/{date}")
    public ResponseEntity<DailySummaryResponse> getEntry(@PathVariable LocalDate date) {
        DailySummaryResponse dailySummaryResponse = diaryService.getDailySummaryByUser(date);
        return ResponseEntity.ok(dailySummaryResponse);
    }

}
