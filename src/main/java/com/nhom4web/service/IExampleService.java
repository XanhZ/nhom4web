package com.nhom4web.service;

import com.nhom4web.model.Example;

import java.util.LinkedHashMap;
import java.util.List;

public interface IExampleService {
  List<Example> layTatCa();

  boolean capNhat(LinkedHashMap<String, Object> duLieu, int ma);

  int them(LinkedHashMap<String, Object> duLieu);

  Example timTheoMa(int ma);

  boolean xoaTheoMa(int ma);
}
