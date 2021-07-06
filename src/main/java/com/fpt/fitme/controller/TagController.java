package com.fpt.fitme.controller;

import com.fpt.fitme.dto.tag.DisableTagDTO;
import com.fpt.fitme.dto.tag.TagDTO;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.service.TagService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    public final static String ERROR_LIST_EMPTY="List empty";

    @Autowired
    private TagService tagService;

    @GetMapping("")
    public ResponseEntity<List<TagDTO>> getAllTags(@RequestParam(required = false,name ="tagType")String tagType) {
        try{
            List<TagDTO> result;

            if(tagType!=null){
                result = tagService.getListTagByType(tagType);
            }else
            {
                result=tagService.getListTag();
            }

            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity(ERROR_LIST_EMPTY, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagByID(@PathVariable("id") long id) {
        TagDTO dto= tagService.getTagByID(id);

        if (dto!=null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity createTag(@RequestBody Tag tag) {
        try {
            TagDTO dto=tagService.createTag(tag);
            return new ResponseEntity(dto,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTag(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            DisableTagDTO dto=tagService.disableTag(id,patch);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody Tag tag) {
        try {
            TagDTO dto=tagService.updateTag(id,tag);
            if(dto!=null){
                return new ResponseEntity(dto,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
