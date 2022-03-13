package com.nhom4web;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class WebAppContextListener implements ServletContextListener  {
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    System.out.println("************** Khoi tao WebAppContextListener **************");
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    System.out.println("************** Dong WebAppContextListener **************");
    System.out.println("Huy Context...");
    System.out.println("MySQL AbandonedConnectionCleanupThread checkedShutdown...");
    AbandonedConnectionCleanupThread.checkedShutdown();

    ClassLoader cl = Thread.currentThread().getContextClassLoader();

    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements()) {
      Driver driver = drivers.nextElement();

      if (driver.getClass().getClassLoader() == cl) {
        try {
          System.out.println("Huy dang ky JDBC driver");
          DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
          System.out.println("Loi huy dang ky JDBC driver");
          e.printStackTrace();
        }
      } else {
        System.out.println("Khong the huy JDBC driver vi khong co trong webapp's ClassLoader");
      }
    }
  }
}
