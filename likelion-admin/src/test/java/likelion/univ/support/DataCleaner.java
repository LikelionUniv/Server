package likelion.univ.support;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Component
@ActiveProfiles("test")
public class DataCleaner {

    private static final String H2_FOREIGN_KEY_CHECK_FORMAT = "SET REFERENTIAL_INTEGRITY %d";
    private static final String TRUNCATE_FORMAT = "TRUNCATE TABLE %s";

    private final List<String> tableNames = new ArrayList<>();

    private DatabaseDriver databaseDriver = null;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void checkDatabase() {
        try {
            databaseDriver = DatabaseDriver.fromJdbcUrl(dataSource.getConnection().getMetaData().getURL());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void findDatabaseTableNames() {
        List<Object[]> tableInfos = entityManager.createNativeQuery("SHOW TABLES").getResultList();
        for (Object[] tableInfo : tableInfos) {
            String tableName = (String) tableInfo[0];
            tableNames.add(tableName);
        }
    }

    @Transactional
    public void clear() {
        entityManager.clear();
        truncate();
    }

    private void truncate() {
        entityManager.createNativeQuery(String.format(H2_FOREIGN_KEY_CHECK_FORMAT, 0)).executeUpdate();
        for (String tableName : tableNames) {
            entityManager.createNativeQuery(String.format(TRUNCATE_FORMAT, tableName)).executeUpdate();
        }
        entityManager.createNativeQuery(String.format(H2_FOREIGN_KEY_CHECK_FORMAT, 1)).executeUpdate();
    }
}
