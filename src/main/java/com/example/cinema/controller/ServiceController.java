package com.example.cinema.controller;

import com.example.cinema.dtos.ServiceDTO;
import com.example.cinema.model.Service;
import com.example.cinema.service.SvService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service")
@RequiredArgsConstructor
public class ServiceController {
    private final SvService svService;
    @PostMapping("")
    public ResponseEntity<?> createService(
            @RequestBody ServiceDTO serviceDTO,
            BindingResult result
    ){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        return ResponseEntity.ok(svService.createService(serviceDTO));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllService(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        PageRequest pageRequest = PageRequest.of(page,limit,Sort.by("name").descending());
        Page<Service> servicePage = svService.getAllService(pageRequest);
        List<Service> services = servicePage.getContent();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(
            @PathVariable int id
    ){
        return ResponseEntity.ok(svService.getServiceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(
            @PathVariable int id,
            @RequestBody ServiceDTO serviceDTO
    ){
        return ResponseEntity.ok(svService.updateService(id,serviceDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getServiceByName(
            @RequestParam("keyword") String keyword
    ){
        List<Service> result =  svService.searchByName(keyword);
        return ResponseEntity.ok(result);
    }
}
