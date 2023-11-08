package com.example.cinema.service;

import com.example.cinema.dtos.ServiceDTO;
import com.example.cinema.exception.CustomException;
import com.example.cinema.model.Service;
import com.example.cinema.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class SvService implements ISvService{
    private final ServiceRepository serviceRepository;
    @Override
    public Service createService(ServiceDTO serviceDTO) {
        Service service = new Service()
                .builder()
                .description(serviceDTO.getDescription())
                .name(serviceDTO.getName())
                .price(serviceDTO.getPrice())
                .build();
        return serviceRepository.save(service);
    }

    @Override
    public Service updateService(int id, ServiceDTO serviceDTO) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isEmpty()){
            throw new CustomException("Không tồn tại service với id : " + id);
        }
        return service.get();
    }

    @Override
    public Service getServiceById(int id) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isEmpty()){
            throw new CustomException("Không tồn tại service với id : " + id);
        }
        return service.get();
    }

    @Override
    public List<Service> searchByName(String keyword) {
        List<Service> serviceList = serviceRepository.findAll();
        List<Service> result = new ArrayList<>();
       for(Service s : serviceList) if(s.getName().contains(keyword)) result.add(s);
        return result;
    }

    @Override
    public Page<Service> getAllService(PageRequest pageRequest) {
        Page<Service> servicePage = serviceRepository.findAll(pageRequest);
        return servicePage;
    }

    @Override
    public void deleteService(int id) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isEmpty()){
            throw new CustomException("Không tồn tại service với id : " + id);
        }
        serviceRepository.deleteById(id);
    }
}
