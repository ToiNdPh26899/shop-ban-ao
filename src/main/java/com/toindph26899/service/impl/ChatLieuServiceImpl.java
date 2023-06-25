package com.toindph26899.service.impl;

import com.toindph26899.entity.ChatLieu;
import com.toindph26899.repository.ChatLieuRepository;
import com.toindph26899.request.ChatLieuRequest;
import com.toindph26899.response.ChatLieuResponse;
import com.toindph26899.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {

    @Autowired
    private ChatLieuRepository chatLieuRepositoryRepository;

    private Page<ChatLieuResponse> page(Pageable pageable, List<ChatLieuResponse> list) {

        int pageNo = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int startItem = pageNo * pageSize;

        List<ChatLieuResponse> pageList;

        if (list.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            pageList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageList, PageRequest.of(pageNo, pageSize), list.size());
    }

    private ChatLieu chatLieu(ChatLieuRequest request) {
        return ChatLieu.builder()
                .id(request.getIdChatLieu())
                .tenChatLieu(request.getTenChatLieu())
                .trangThai(request.getTrangThai())
                .build();
    }

    private ChatLieuResponse chatLieuResponse(Long id) {

        Optional<ChatLieu> chatLieuOptional = chatLieuRepositoryRepository.findById(id);

        ChatLieu chatLieu = null;

        if (chatLieuOptional.isPresent()) {
            chatLieu = chatLieuOptional.get();
        } else {
            return null;
        }

        return ChatLieuResponse.builder()
                .idChatLieu(chatLieu.getId())
                .tenChatLieu(chatLieu.getTenChatLieu())
                .trangThai(chatLieu.getTrangThai())
                .build();
    }

    @Override
    public Page<ChatLieuResponse> findAll(Pageable pageable) {
        return page(pageable, chatLieuRepositoryRepository.findAllCustom());
    }

    @Override
    public List<ChatLieuResponse> findAll() {
        return chatLieuRepositoryRepository.findAllCustom();
    }

    @Override
    public void save(ChatLieuRequest request) {
        chatLieuRepositoryRepository.save(chatLieu(request));
    }

    @Override
    public ChatLieuResponse getOne(Long id) {
        return chatLieuResponse(id);
    }
}
