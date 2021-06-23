package com.fpt.fitme.repository;

import com.fpt.fitme.entity.tag.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
