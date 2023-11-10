package com.channeltalk.teamten.post.repository;

import com.channeltalk.teamten.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMemberKeyIdAndDeadline(Long memberKeyId, Long deadline);
}
