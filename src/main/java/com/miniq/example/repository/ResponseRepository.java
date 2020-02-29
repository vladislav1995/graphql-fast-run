package com.miniq.example.repository;

import com.google.common.collect.ImmutableList;
import com.miniq.example.dto.ResponseDto;
import com.miniq.example.mapper.ResponseRowMapper;
import com.miniq.example.provider.JdbcTemplateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Uladik
 */
@Repository
public class ResponseRepository implements CrudRepository<ResponseDto, Long> {

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM fast_run.response WHERE response.id = ?";
    private static final String SELECT_BY_METHOD_QUERY = "SELECT * FROM fast_run.response WHERE response.method = ?";
    private static final String INSERT_QUERY = "INSERT INTO fast_run.response (method, status, message, last_update_date) VALUES (?, ?, ?, ?)";

    private JdbcTemplate template;
    private ResponseRowMapper responseRowMapper;

    @Autowired
    public ResponseRepository(JdbcTemplateProvider provider, ResponseRowMapper responseRowMapper) {
        this.template = provider.getTemplate();
        this.responseRowMapper = responseRowMapper;
    }

    @Override
    public ResponseDto get(Long id) {
        return template.queryForObject(SELECT_BY_ID_QUERY, responseRowMapper, id);
    }

    public List<ResponseDto> getByMethod(String method) {
        return template.query(SELECT_BY_METHOD_QUERY, responseRowMapper, method);
    }

    @Override
    public Long put(ResponseDto data) {
        return (long) template.update(INSERT_QUERY, convertData(data));
    }

    @Override
    public Long update(ResponseDto data) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    private Object[] convertData(ResponseDto data) {
        return ImmutableList.of(data.getMethod(), data.getStatusCode(), data.getMessage(), new Date()).toArray();
    }
}
