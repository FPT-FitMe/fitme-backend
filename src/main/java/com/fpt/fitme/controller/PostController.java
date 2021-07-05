package com.fpt.fitme.controller;

import com.fpt.fitme.dto.post.DisablePostDTO;
import com.fpt.fitme.dto.post.PostDTO;
import com.fpt.fitme.entity.post.Post;
import com.fpt.fitme.service.PostService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(required = false,name ="coachID")Long coachID) {
        try{
            List<PostDTO> result;

            //neu la co param coachID thi getListByCoach
            if(coachID!=null){
                result = postService.getListPostByCoach(coachID);
            }else //khong thi get binh thuong
            {
                result=postService.getListPost();
            }

            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostByID(@PathVariable("id") long id) {
        PostDTO dto= postService.getPostByID(id);

        if (dto!=null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity createPost(@RequestBody Post post) {
        try {
            PostDTO dto=postService.createPost(post);
            return new ResponseEntity(dto,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchPost(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            DisablePostDTO dto=postService.disablePost(id,patch);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody Post post) {
        try {
            PostDTO dto=postService.updatePost(id,post);
            if(dto!=null){
                return new ResponseEntity(dto,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}