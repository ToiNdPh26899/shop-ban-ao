package com.toindph26899.service;

import com.toindph26899.request.ChatLieuRequest;
import com.toindph26899.response.ChatLieuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatLieuService {

    Page<ChatLieuResponse> findAll(Pageable pageable);

    List<ChatLieuResponse> findAll();

    void save(ChatLieuRequest request);

    ChatLieuResponse getOne(Long id);
}
