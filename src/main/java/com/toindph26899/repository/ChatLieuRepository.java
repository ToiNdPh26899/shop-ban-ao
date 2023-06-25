package com.toindph26899.repository;

import com.toindph26899.entity.ChatLieu;
import com.toindph26899.response.ChatLieuResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, Long> {

    @Query("select new com.toindph26899.response.ChatLieuResponse(cl.id, cl.tenChatLieu, cl.trangThai) from ChatLieu cl")
    List<ChatLieuResponse> findAllCustom();

}
