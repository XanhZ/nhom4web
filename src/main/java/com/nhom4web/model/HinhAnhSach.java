package com.nhom4web.model;

public class HinhAnhSach extends AbstractModel {
    private String duongDan;
    private String publicId;

    public HinhAnhSach() {

    }

    public HinhAnhSach(int ma, String duongDan, String publicId) {
        super(ma);
        this.duongDan = duongDan;
        this.publicId = publicId;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }
}
