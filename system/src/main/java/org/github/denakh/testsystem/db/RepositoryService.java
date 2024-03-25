package org.github.denakh.testsystem.db;


import java.util.Properties;

import lombok.Getter;
import org.github.denakh.testsystem.db.dao.TestObjectDaoImpl;
import org.github.denakh.testsystem.model.db.TestObject;
import org.github.denakh.util.StringUtility;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Getter
public class RepositoryService {

    private final TestObjectDaoImpl testObjectDao;

    public RepositoryService() {
        final String dbUrlParamName = "DBUrl";
        final String dbUserParamName = "DBUser";
        final String dbPasswordParamName = "DBPassword";
        String jdbcUrl = System.getenv(dbUrlParamName);
        String user = System.getenv(dbUserParamName);
        String pass = System.getenv(dbPasswordParamName);
        SessionFactory sessionFactory = configureHibernate(jdbcUrl, user, pass);
        EntityManagerUtil entityManagerUtil = new EntityManagerUtil(sessionFactory);
        testObjectDao = new TestObjectDaoImpl(entityManagerUtil);
    }

    private static SessionFactory configureHibernate(String jdbcUrl, String user, String pass) {
        final String hibernateParamName = "hibernate";
        final String connectionParamName = "connection";
        final String urlParamName = "url";
        final String dialectParamName = "dialect";
        final String orgParamName = "org";
        final String mySQLDialectParamName = "MySQLDialect";
        final String usernameParamName = "username";
        final String passwordParamName = "password";
        final String driverClassParamName = "driver_class";
        final String showSqlParamName = "show_sql";
        final String formatSqlParamName = "format_sql";
        final String driverParamName = "com.mysql.jdbc.Driver";
        Properties properties = new Properties();
        properties.setProperty(String.join(StringUtility.POINT, hibernateParamName, connectionParamName, urlParamName), jdbcUrl);
        properties.setProperty(dialectParamName, String.join(StringUtility.POINT, orgParamName, hibernateParamName, dialectParamName,
                mySQLDialectParamName));
        properties.setProperty(String.join(StringUtility.POINT, hibernateParamName, connectionParamName, usernameParamName), user);
        properties.setProperty(String.join(StringUtility.POINT, hibernateParamName, connectionParamName, passwordParamName), pass);
        properties.setProperty(String.join(StringUtility.POINT, hibernateParamName, connectionParamName, driverClassParamName),
                driverParamName);
        properties.setProperty(String.join(StringUtility.POINT, hibernateParamName, showSqlParamName), Boolean.toString(false));
        properties.setProperty(String.join(StringUtility.POINT, hibernateParamName, formatSqlParamName), Boolean.toString(false));

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(TestObject.class);

        return configuration.addProperties(properties).buildSessionFactory();
    }

    public void removeAllTestDataFromDB() {
        testObjectDao.removeAll();
    }

}
