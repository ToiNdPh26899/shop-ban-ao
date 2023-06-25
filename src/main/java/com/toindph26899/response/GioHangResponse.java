package com.toindph26899.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class GioHangResponse {

    private List<SanPhamChiTietResponse> cart = new ArrayList<>();

    public void addItem(SanPhamChiTietResponse item) {
        this.cart.add(item);
    }

    public void updateItem(int index, SanPhamChiTietResponse item) {
        this.cart.set(index, item);
    }

    public List<SanPhamChiTietResponse> getCart() {
        return cart;
    }

    public void clear() {
        this.cart.clear();
    }

}
