package com.channeltalk.teamten.post.repository;

import com.channeltalk.teamten.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
