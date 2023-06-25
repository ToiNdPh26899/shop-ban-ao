package com.toindph26899.request;

import com.toindph26899.response.SanPhamChiTietResponse;
import com.toindph26899.validation.NotEmptyList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DonHangRequest {

    private Long idDonHang;

    private List<Long> idSanPham;

    @NotBlank(message = "* Nhập tên nguười nhận")
    private String tenNguoiNhan;

    @NotBlank(message = "* Vui lòng nhập địa chỉ nhận hàng")
    private String diaChiNguoiNhan;

    @NotBlank(message = "* Số điện thoại không được bỏ trống")
    private String soDienThoaiNguoiNhan;

    @NotBlank(message = "* Email không được bỏ trống")
    private String email;

    private String ghiChu;

    @NotNull(message = "* Chọn hình thức thanh toán")
    private Integer hinhThucThanhToan;

    private List<String> idKichCo;

    private List<String> idMauSac;

    private Integer trangThai;

}
