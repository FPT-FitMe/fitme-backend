package com.fpt.fitme.repository;

import com.fpt.fitme.entity.tag.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> getTagsByType(String type);
}
