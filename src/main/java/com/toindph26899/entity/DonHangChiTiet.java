package com.toindph26899.entity;

import com.toindph26899.response.TraCuuDonHang;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@NamedNativeQuery(name = "DonHangChiTiet.traCuuDonHangs",
        query = "  select dh.id as Id, dh.ten_nguoi_nhan as NguoiNhan, \n" +
                "  dh.so_dien_thoai_nguoi_nhan as SoDienThoai, \n" +
                "  dh.dia_chi_nhan_hang as DiaChi, sp.ten_san_pham as TenSanPham, \n" +
                "  dhct.so_luong as SoLuong, kc.size as KichCo, \n" +
                "  ms.ten_mau_sac as MauSac, asp.duong_dan_anh as DuongDanAnh, \n" +
                "  sp.gia_giao_ban as GiaBan\n" +
                "  from don_hang_chi_tiet dhct \n" +
                "  join don_hang dh on dh.id = dhct.id_don_hang\n" +
                "  join san_pham sp on dhct.id_san_pham = sp.Id\n" +
                "  join san_pham_kich_co_mau_sac spkcms on sp.Id = spkcms.id_san_pham\n" +
                "  join mau_sac ms on spkcms.id_mau_sac = ms.id\n" +
                "  join kich_co kc on spkcms.id_kich_co = kc.Id\n" +
                "  join anh_san_pham asp on asp.id_san_pham = sp.id\n" +
                "  where dh.so_dien_thoai_nguoi_nhan = ?",
        resultSetMapping = "Mapping.traCuuDonHang"
)

@SqlResultSetMapping(name = "Mapping.traCuuDonHang",
        classes = @ConstructorResult(
                targetClass = TraCuuDonHang.class,
                columns = {
                        @ColumnResult(name = "Id"),
                        @ColumnResult(name = "NguoiNhan"),
                        @ColumnResult(name = "SoDienThoai"),
                        @ColumnResult(name = "DiaChi"),
                        @ColumnResult(name = "TenSanPham"),
                        @ColumnResult(name = "SoLuong"),
                        @ColumnResult(name = "KichCo"),
                        @ColumnResult(name = "MauSac"),
                        @ColumnResult(name = "DuongDanAnh"),
                        @ColumnResult(name = "GiaBan"),
                }
        )
)

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "don_hang_chi_tiet")
public class DonHangChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_don_hang")
    private DonHang idDonHang;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    private SanPham idSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai")
    private Integer trangThai;

}
