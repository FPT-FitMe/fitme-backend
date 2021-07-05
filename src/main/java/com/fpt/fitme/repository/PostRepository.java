package com.fpt.fitme.repository;

import com.fpt.fitme.entity.post.Post;
import com.fpt.fitme.entity.workout.CoachProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> getPostsByCoachProfile(CoachProfile coachProfile);
}