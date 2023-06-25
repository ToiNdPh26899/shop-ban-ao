package com.toindph26899.entity;

import com.toindph26899.response.MauSacResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NamedNativeQuery(name = "MauSac.findAllMauSacByIdSanPham",
        query = "select ms.id as Id, ms.ten_mau_sac as TenMauSac, \n" +
                "\t\tms.trang_thai as TrangThai \n" +
                "\tfrom san_pham_kich_co_mau_sac spkcms \n" +
                "\t\tjoin mau_sac ms on spkcms.id_mau_sac = ms.id\n" +
                "\twhere id_san_pham = ?\n" +
                "\tgroup by ms.id, ms.ten_mau_sac, ms.trang_thai",
        resultSetMapping = "Entity.MauSacResponse"
)

@SqlResultSetMapping(name = "Entity.MauSacResponse",
        classes = @ConstructorResult(targetClass = MauSacResponse.class,
                columns = {
                        @ColumnResult(name = "Id"),
                        @ColumnResult(name = "TenMauSac"),
                        @ColumnResult(name = "TrangThai")
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
@Table(name = "mau_sac")
public class MauSac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_mau_sac")
    private String tenMauSac;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @OneToMany(mappedBy = "idMauSac")
    private List<SanPhamKichCoMauSac> sanPhamMauSacs;
}
