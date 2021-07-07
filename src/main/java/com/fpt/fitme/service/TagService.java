package com.fpt.fitme.service;

import com.fpt.fitme.dto.tag.DisableTagDTO;
import com.fpt.fitme.dto.tag.TagDTO;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.repository.TagRepository;
import com.fpt.fitme.util.JsonPatcherUtil;
import com.github.fge.jsonpatch.JsonPatch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TagDTO> getListTag() {
        List<TagDTO> result = new ArrayList<>();
        tagRepository.findAll().forEach(tag -> {
            if (tag.getIsActive()) {
                TagDTO dto = modelMapper.map(tag, TagDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public List<TagDTO> getListTagByType(String type) {
        List<TagDTO> result = new ArrayList<>();
        tagRepository.getTagsByType(type).forEach(tag -> {
            if (tag.getIsActive()) {
                TagDTO dto = modelMapper.map(tag, TagDTO.class);
                result.add(dto);
            }
        });
        return result;
    }


    public TagDTO getTagByID(long id) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if (tagOptional.isPresent() && tagOptional.get().getIsActive())
            return modelMapper.map(tagOptional.get(), TagDTO.class);
        return null;
    }

    public TagDTO createTag(Tag tag) throws Exception {
        tag.setIsActive(true);

        Tag savedTag = tagRepository.save(tag);
        return modelMapper.map(savedTag, TagDTO.class);
    }

    public TagDTO patchTag(Long id, JsonPatch patch) throws Exception {
        Optional<Tag> currentTag = tagRepository.findById(id);

        if (!(currentTag.isPresent() && currentTag.get().getIsActive()))
            throw new Exception("tagID not found!");

        Tag tagPatched = (Tag) JsonPatcherUtil.applyPatch(patch, currentTag.get());
        tagRepository.save(tagPatched);
        return modelMapper.map(tagPatched, TagDTO.class);
    }

    public DisableTagDTO disableTag(Long id, JsonPatch patch) throws Exception {
        Optional<Tag> currentTag = tagRepository.findById(id);

        if (!(currentTag.isPresent() && currentTag.get().getIsActive()))
            throw new Exception("tagID not found!");

        Tag tagPatched = (Tag) JsonPatcherUtil.applyPatch(patch, currentTag.get());
        tagRepository.save(tagPatched);

        return modelMapper.map(tagPatched, DisableTagDTO.class);
    }

    public TagDTO updateTag(Long id, Tag tag) throws Exception {
        Optional<Tag> optionalTag = tagRepository.findById(id);

        if (!(optionalTag.isPresent() && optionalTag.get().getIsActive())) throw new Exception("tagID not found!");
        Tag tagToUpdate = optionalTag.get();
        tagToUpdate.setName(tag.getName());
        tagToUpdate.setType(tag.getType());
        tagRepository.save(tagToUpdate);

        return modelMapper.map(tagToUpdate, TagDTO.class);

    }
}
