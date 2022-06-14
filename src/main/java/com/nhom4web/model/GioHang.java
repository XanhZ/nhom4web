package com.nhom4web.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class GioHang {
    private final HashSet<SachTrongGioHang> sachs;

    public GioHang() {
        this.sachs = new HashSet<>();
    }

    public HashSet<SachTrongGioHang> getSachs() {
        return sachs;
    }

    public boolean themSach(SachTrongGioHang sach) {
        return this.sachs.add(sach);
    }

    public SachTrongGioHang tim(int maSach) {
        for (SachTrongGioHang sach : sachs) {
            if (sach.getSach().getMa() == maSach) return sach;
        }
        return null;
    }

    public void loaiBo(Collection<Integer> maSachs) {
        this.sachs.removeIf(o -> maSachs.contains(o.getSach().getMa()));
    }
}
