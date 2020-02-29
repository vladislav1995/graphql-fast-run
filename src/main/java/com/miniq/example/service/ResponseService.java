package com.miniq.example.service;

import com.miniq.example.dto.ResponseDto;
import com.miniq.example.repository.CrudRepository;
import com.miniq.example.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Uladik
 */
@Service
public class ResponseService implements CrudRepository<ResponseDto, Long> {

    private ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public ResponseDto get(Long id) {
        return responseRepository.get(id);
    }

    public List<ResponseDto> getByMethod(String method) {
        return responseRepository.getByMethod(method);
    }

    @Override
    public Long put(ResponseDto data) {
        return responseRepository.put(data);
    }

    @Override
    public Long update(ResponseDto data) {
        return responseRepository.update(data);
    }

    @Override
    public boolean delete(Long id) {
        return responseRepository.delete(id);
    }
}
