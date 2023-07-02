package com.toindph26899.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tai_khoan")
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tai_khoan_chuc_vu",
            joinColumns = @JoinColumn(name = "id_tai_khoan"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Collection<ChucVu> roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_tai_khoan")
    private KhachHang idKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhan_vien_tai_khoan")
    private NhanVien idNhanVien;

}
