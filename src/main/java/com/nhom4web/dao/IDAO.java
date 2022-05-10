package com.nhom4web.dao;

import java.util.List;

public interface IDAO<T> {
    boolean capNhat(T t);

    List<T> layTatCa();

    T tim(int ma);

    boolean them(T t);

    boolean xoa(int ma);
}