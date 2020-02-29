package com.miniq.example.resolver;

import com.miniq.example.dto.ResponseDto;
import com.miniq.example.service.ResponseService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Uladik
 */
@Component
public class MutationResolver {

    private ResponseService responseService;

    @Autowired
    public MutationResolver(ResponseService responseService) {
        this.responseService = responseService;
    }

    public DataFetcher<Long> putResponse() {
        return dataFetchingEnvironment -> {
            ResponseDto data = convertInputData(dataFetchingEnvironment.getArgument("responseBody"));
            return responseService.put(data);
        };
    }

    private ResponseDto convertInputData(Map<String, Object> inputData) {
        ResponseDto dto = new ResponseDto();
        dto.setMethod((String) inputData.get("method"));
        dto.setStatusCode((Integer) inputData.get("statusCode"));
        dto.setMessage((String) inputData.get("message"));
        return dto;
    }
}
