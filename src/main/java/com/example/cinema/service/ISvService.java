package com.example.cinema.service;


import com.example.cinema.dtos.ServiceDTO;
import com.example.cinema.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ISvService {
    Service createService(ServiceDTO serviceDTO);
    Service updateService(int id, ServiceDTO serviceDTO);
    Service getServiceById(int id);
    List<Service> searchByName(String keyword);
    Page<Service> getAllService(PageRequest pageRequest);
    void deleteService(int id);
}
