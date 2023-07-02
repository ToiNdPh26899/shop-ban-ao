package com.toindph26899.entity;

import com.toindph26899.response.SanPhamView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@NamedNativeQuery(name = "SanPham.listHienThi",
        query = "select\n" +
                "\tsp.id as Id, sp.ten_san_pham as TenSanPham, \n" +
                "\tsp.gia_giao_ban as GiaGiaoBan, asp.duong_dan_anh as DuongDanAnh \n" +
                "\tfrom san_pham sp \n" +
                "\tjoin san_pham_kich_co_mau_sac on sp.id = san_pham_kich_co_mau_sac.id_san_pham\n" +
                "\tjoin kich_co on kich_co.id = san_pham_kich_co_mau_sac.id_kich_co\n" +
                "    join anh_san_pham asp on asp.id_san_pham = sp.id\n" +
                "    group by sp.id, sp.ten_san_pham, sp.gia_giao_ban, asp.duong_dan_anh",
        resultSetMapping = "Mapping.SanPhamView"
)

@SqlResultSetMapping(name = "Mapping.SanPhamView",
        classes = @ConstructorResult(targetClass = SanPhamView.class,
                columns = {
                        @ColumnResult(name = "Id"),
                        @ColumnResult(name = "TenSanPham"),
                        @ColumnResult(name = "GiaGiaoBan"),
                        @ColumnResult(name = "DuongDanAnh"),
                }
        )
)

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "san_pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_san_pham")
    private String maSanPham;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "gia_giao_ban")
    private BigDecimal giaGiaoBan;

    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "anh_mac_dinh")
    private String anhMacDinh;

    @ManyToOne
    @JoinColumn(name = "id_chat_lieu")
    private ChatLieu idChatLieu;

    @ManyToOne
    @JoinColumn(name = "id_co_ao")
    private CoAo idCoAo;

    @ManyToOne
    @JoinColumn(name = "id_loai_ao")
    private LoaiAo idLoaiAo;

    @ManyToOne
    @JoinColumn(name = "id_thuong_hieu")
    private ThuongHieu idThuongHieu;

    @ManyToOne
    @JoinColumn(name = "id_tay_ao")
    private TayAo idTayAo;

    @OneToMany(mappedBy = "idSanPham")
    private List<SanPhamKichCoMauSac> sanPhamKichCos;

    @OneToMany(mappedBy = "idSanPham")
    private List<AnhSanPham> anhSanPhams;

}
