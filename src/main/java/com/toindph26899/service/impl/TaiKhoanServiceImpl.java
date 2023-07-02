package com.toindph26899.service.impl;

import com.toindph26899.dao.ChucVuDao;
import com.toindph26899.dao.TaiKhoanDao;
import com.toindph26899.entity.ChucVu;
import com.toindph26899.entity.GioHang;
import com.toindph26899.entity.KhachHang;
import com.toindph26899.entity.TaiKhoan;
import com.toindph26899.repository.ChucVuRepository;
import com.toindph26899.repository.GioHangRepository;
import com.toindph26899.repository.KhachHangRepository;
import com.toindph26899.repository.TaiKhoanRepository;
import com.toindph26899.request.DangKiTaiKhoan;
import com.toindph26899.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public TaiKhoan findByUser(String username) {
        return taiKhoanRepository.findTaiKhoanByUsername(username);
    }

    @Override
    public TaiKhoan checkLogin() {
        TaiKhoan taiKhoan = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated() && authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof User) {
                User user = (User) principal;

                taiKhoan = taiKhoanRepository.findTaiKhoanByUsername(user.getUsername());

            }
        }

        return taiKhoan;
    }

    @Override
    public void saveUser(DangKiTaiKhoan dangKiTaiKhoan) {

        int random = (int) (Math.random() * 999999999);

        KhachHang khachHang = KhachHang.builder()
                .tenKhachHang(dangKiTaiKhoan.getFirstName() + " " + dangKiTaiKhoan.getLastName())
                .email(dangKiTaiKhoan.getEmail())
                .soDienThoai(String.valueOf(random))
                .build();

        KhachHang khachHangDb = khachHangRepository.save(khachHang);

        GioHang gioHang = GioHang.builder()
                .ngayTao(Date.valueOf(LocalDate.now()))
                .nguoiSoHuu(khachHangDb)
                .build();

        gioHangRepository.save(gioHang);

        TaiKhoan taiKhoan = TaiKhoan.builder()
                .username(dangKiTaiKhoan.getUsername())
                .password(passwordEncoder.encode(dangKiTaiKhoan.getPassword()))
                .trangThai(1)
                .roles(Collections.singletonList(chucVuRepository.findChucVuByRole("ROLE_CUSTOMER")))
                .idKhachHang(khachHangDb)
                .build();

        taiKhoanRepository.save(taiKhoan);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TaiKhoan taiKhoan = taiKhoanRepository.findTaiKhoanByUsername(username);

        if (taiKhoan == null) {
            throw new UsernameNotFoundException("Tai khoan hoac mat khau khong chinh xac");
        }

        return new org.springframework.security.core.userdetails.User(taiKhoan.getUsername(),
                taiKhoan.getPassword(),
                mapRolesToAuthorities(taiKhoan.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<ChucVu>
                                                                                 roles) {
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

}
