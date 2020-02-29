package com.miniq.example.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import static java.util.Objects.requireNonNull;
import static org.springframework.jdbc.datasource.DataSourceUtils.getConnection;
import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;

/**
 * @author Uladik
 */
@Component
public class JdbcTemplateProvider {

    private JdbcTemplate template;

    @Autowired
    public JdbcTemplateProvider(DataSource source) {
        this.template = new JdbcTemplate(source);
        initSchema();
    }

    public JdbcTemplate getTemplate() {
        return this.template;
    }

    private void initSchema() {
        Resource initScript = new ClassPathResource("initScript.sql");
        executeSqlScript(getConnection(requireNonNull(template.getDataSource())), initScript);
    }
}
