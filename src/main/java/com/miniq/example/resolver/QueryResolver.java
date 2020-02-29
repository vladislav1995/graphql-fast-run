package com.miniq.example.resolver;

import com.miniq.example.dto.ResponseDto;
import com.miniq.example.service.ResponseService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Uladik
 */
@Component
public class QueryResolver {

    private ResponseService responseService;

    @Autowired
    public QueryResolver(ResponseService responseService) {
        this.responseService = responseService;
    }

    public DataFetcher<ResponseDto> getResponse() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return responseService.get(Long.parseLong(id));
        };
    }

    public DataFetcher<List<ResponseDto>> getResponsesByMethod() {
        return dataFetchingEnvironment -> {
            String method = dataFetchingEnvironment.getArgument("method");
            return responseService.getByMethod(method);
        };
    }

}
