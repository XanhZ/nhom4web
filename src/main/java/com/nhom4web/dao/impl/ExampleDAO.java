package com.nhom4web.dao.impl;

import com.nhom4web.dao.IExampleDAO;
import com.nhom4web.model.Example;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExampleDAO extends AbstractDAO<Example> implements IExampleDAO {
  public ExampleDAO() {
    super.setTenBang("examples");
  }

  @Override
  protected Example sangThucThe(ResultSet rs) {
    try {
      int ma = rs.getInt(1);
      String hoTen = rs.getString(2);
      int tuoi = rs.getInt(3);
      return new Example(ma, hoTen, tuoi);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
