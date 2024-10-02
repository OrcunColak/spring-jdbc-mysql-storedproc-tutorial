package com.colak.springtutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public ProductService(JdbcTemplate jdbcTemplate) {
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getDistinctProducts")  // name of the stored procedure
                .declareParameters(
                        new SqlParameter("category", Types.VARCHAR),
                        new SqlParameter("minPrice", Types.DECIMAL),
                        new SqlParameter("maxPrice", Types.DECIMAL),
                        new SqlParameter("availability", Types.VARCHAR)
                )
                // Specify the return type using BeanPropertyRowMapper
                .returningResultSet("#result-set-1", BeanPropertyRowMapper.newInstance(Product.class));
    }

    public List<Product> getDistinctProducts(String category, double minPrice, double maxPrice, String availability) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        params.put("minPrice", minPrice);
        params.put("maxPrice", maxPrice);
        params.put("availability", availability);

        Map<String, Object> result = simpleJdbcCall.execute(params);

        return (List<Product>) result.get("#result-set-1");
    }
}

