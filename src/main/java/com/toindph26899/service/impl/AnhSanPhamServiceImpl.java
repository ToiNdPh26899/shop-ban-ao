package com.toindph26899.service.impl;

import com.toindph26899.entity.AnhSanPham;
import com.toindph26899.entity.SanPham;
import com.toindph26899.repository.AnhSanPhamRepository;
import com.toindph26899.repository.SanPhamRepository;
import com.toindph26899.request.AnhSanPhamRequest;
import com.toindph26899.service.AnhSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AnhSanPhamServiceImpl implements AnhSanPhamService {

    @Autowired
    private AnhSanPhamRepository anhSanPhamRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    private AnhSanPham anhSanPham(AnhSanPhamRequest request, MultipartFile file) {

        Optional<SanPham> sanPhamOptional = sanPhamRepository.findById(request.getIdSanPham());

        SanPham sanPham = null;

        if (sanPhamOptional.isPresent()) {
            sanPham = sanPhamOptional.get();
        } else {
            return null;
        }

        String currentDirectory = System.getProperty("user.dir");
        String absoluteFilePath = currentDirectory + "/src/main/resources/static/image/products/";
        String displayImage = "/image/products/";
        String fileName = file.getOriginalFilename();
        String filePath = absoluteFilePath + fileName;

        try {
            file.transferTo(new File(filePath));
            return AnhSanPham.builder()
                    .id(request.getIdAnh())
                    .idSanPham(sanPham)
                    .trangThai(request.getTrangThai())
                    .duongDanAnh(displayImage + fileName)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private AnhSanPhamRequest anhSanPhamRequest(Long id) {
        Optional<AnhSanPham> anhSanPhamOptional = anhSanPhamRepository.findById(id);

        AnhSanPham anhSanPham = null;

        if (anhSanPhamOptional.isPresent()) {
            anhSanPham = anhSanPhamOptional.get();
        } else {
            return null;
        }

        return AnhSanPhamRequest.builder()
                .idAnh(anhSanPham.getId())
                .idSanPham(anhSanPham.getIdSanPham().getId())
                .duongDanAnh(anhSanPham.getDuongDanAnh())
                .trangThai(anhSanPham.getTrangThai())
                .build();
    }

    @Override
    public void save(AnhSanPhamRequest request, MultipartFile file) {
        anhSanPhamRepository.save(anhSanPham(request, file));
    }

    @Override
    public AnhSanPhamRequest getOne(Long id) {
        return anhSanPhamRequest(id);
    }

}
