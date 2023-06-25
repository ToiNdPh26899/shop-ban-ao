package com.toindph26899.entity;

import com.toindph26899.response.KichCoResponse;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NamedNativeQuery(name = "KichCo.findAllKichCo",
        query = "select kc.Id as Id, kc.size as KichCo, kc.trang_thai as TrangThai\n" +
                "from san_pham_kich_co_mau_sac spkc \n" +
                "join kich_co kc on spkc.id_kich_co = kc.Id\n" +
                "where spkc.id_san_pham = ?\n" +
                "group by kc.Id, kc.size, kc.trang_thai\n",
        resultSetMapping = "Mapping.KichCoResponse"
)

@SqlResultSetMapping(name = "Mapping.KichCoResponse",
        classes = @ConstructorResult(targetClass = KichCoResponse.class,
                columns = {
                        @ColumnResult(name = "Id"),
                        @ColumnResult(name = "KichCo"),
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
@Table(name = "kich_co")
public class KichCo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "size")
    private String loaiKichCo;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @OneToMany(mappedBy = "idKichCo")
    private List<SanPhamKichCoMauSac> sanPhamKichCos;

}
