package com.nhom4web.service.impl;

import com.nhom4web.dao.impl.ExampleDAO;
import com.nhom4web.model.Example;
import com.nhom4web.service.IExampleService;

import java.util.LinkedHashMap;
import java.util.List;

public class ExampleService implements IExampleService {
    private static ExampleDAO exampleDAO = new ExampleDAO();

    @Override
    public List<Example> layTatCa() {
        return exampleDAO.layTatCa();
    }

    @Override
    public boolean capNhat(LinkedHashMap<String, Object> duLieu, int ma) {
        return exampleDAO.capNhat(duLieu, ma);
    }

    @Override
    public int them(LinkedHashMap<String, Object> duLieu) {
        return exampleDAO.them(duLieu);
    }

    @Override
    public Example timTheoMa(int ma) {
        return exampleDAO.layTheoMa(ma);
    }

    @Override
    public boolean xoaTheoMa(int ma) {
        return exampleDAO.xoaTheoMa(ma);
    }
}
