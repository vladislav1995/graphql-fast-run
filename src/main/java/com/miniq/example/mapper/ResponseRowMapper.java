package com.miniq.example.mapper;

import com.miniq.example.dto.ResponseDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Uladik
 */
@Component
public class ResponseRowMapper implements RowMapper<ResponseDto> {

    @Override
    public ResponseDto mapRow(ResultSet resultSet, int i) throws SQLException {
        ResponseDto result = new ResponseDto();
        result.setId(resultSet.getLong("id"));
        result.setMessage(resultSet.getString("message"));
        result.setMethod(resultSet.getString("method"));
        result.setStatusCode(resultSet.getInt("status"));
        result.setDate(resultSet.getDate("last_update_date"));
        return result;
    }

}
