package com.nhom4web.dao;

import java.util.List;

public interface IDAO<T> {
    boolean capNhat(T t, boolean luu);

    boolean capNhat(List<T> list, boolean luu);

    List<T> layTatCa();

    T tim(int ma);

    boolean them(T t, boolean luu);

    boolean them(List<T> list, boolean luu);

    boolean xoa(int ma, boolean luu);
}