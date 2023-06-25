package com.toindph26899.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "don_hang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_hoa_don")
    private String maHoaDon;

    @Column(name = "ma_nhan_vien")
    private String maNhanVien;

    @Column(name = "ma_khach_hang")
    private String maKhachHang;

    @Column(name = "dia_chi_nhan_hang")
    private String diaChiNhanHang;

    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;

    @Column(name = "so_dien_thoai_nguoi_nhan")
    private String soDienThoaiNguoiNhan;

    @Column(name = "email")
    private String email;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "tien_khach_dua")
    private BigDecimal tienKhachDua;

    @Column(name = "giam_gia")
    private BigDecimal giamGia;

    @Column(name = "tien_thua")
    private BigDecimal tienThua;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_ship")
    private Date ngayShip;

    @Column(name = "ngay_nhan")
    private Date ngayNhan;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "hinh_thuc_nhan_hang")
    private String hinhThucNhanHang;

    @Column(name = "hinh_thuc_thanh_toan")
    private Integer hinhThucThanhToan;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang idKhachHang;

    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien idNhanVien;

    @OneToMany(mappedBy = "idDonHang")
    private List<DonHangChiTiet> donHangChiTiets;

}
