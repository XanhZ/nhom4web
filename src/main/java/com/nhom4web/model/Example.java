package com.nhom4web.model;

public class Example extends AbstractModel {
  private int ma;
  private String hoTen;
  private int tuoi;

  public Example(int ma, String hoTen, int tuoi) {
    this.ma = ma;
    this.hoTen = hoTen;
    this.tuoi = tuoi;
  }

  public int getMa() {
    return ma;
  }

  public String getHoTen() {
    return hoTen;
  }

  public int getTuoi() {
    return tuoi;
  }
}
