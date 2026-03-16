package com.example.bookmanagement;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    private String maSach;
    private String tenSach;
    private String tacGia;
    private double donGia;
    /** Xóa mềm: true = đã xóa, không hiển thị trong API */
    private boolean deleted = false;

    public Book() {
    }

    public Book(String maSach, String tenSach, String tacGia, double donGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.donGia = donGia;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }



    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Ma: " + maSach + " | Ten: " + tenSach + " | Tac gia: " + tacGia + " | Don gia: " + donGia;
    }
}
