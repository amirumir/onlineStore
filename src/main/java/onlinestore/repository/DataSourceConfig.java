package onlinestore.repository;

import javax.sql.DataSource;
import org.postgresql.ds.PGPoolingDataSource;

public class DataSourceConfig {
    private static DataSource dataSource;

    private DataSourceConfig() {

    }
    public static synchronized DataSource getDataSource() {

        if (dataSource == null) {
            dataSource = dataSource();
        }
        return dataSource;
    }
    public static synchronized DataSource dataSource() {

        if (dataSource == null) {
            PGPoolingDataSource dataSource1 = new PGPoolingDataSource();
            dataSource1.setDataSourceName("A Data Source");
            dataSource1.setServerNames(new String[]{"127.0.0.1"});
            dataSource1.setPortNumber(5432);
            dataSource1.setDatabaseName("online_store");
            dataSource1.setUser("postgres");
            dataSource1.setPassword("Fail4true1");
            dataSource1.setMaxConnections(10);

            dataSource = dataSource1;
        }

        return dataSource;
    }
}
