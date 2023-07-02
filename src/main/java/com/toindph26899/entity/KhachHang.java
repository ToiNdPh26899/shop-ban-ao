package com.toindph26899.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "khach_hang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_khach_hang")
    private String maKhachHang;

    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @Column(name = "sinh_nhat")
    private Date sinhNhat;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @Column(name = "can_cuoc_cong_dan")
    private String canCuocCongDan;

    @Column(name = "anh_dai_dien")
    private String anhDaiDien;

    @Column(name = "diem_tich_luy")
    private Integer diemTichLuy;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @OneToMany(mappedBy = "idKhachHang")
    private List<DonHang> donHangs;

    @OneToMany(mappedBy = "nguoiSoHuu")
    private List<GioHang> gioHangs;

    @OneToMany(mappedBy = "idKhachHang")
    private List<TaiKhoan> taiKhoans;

}
