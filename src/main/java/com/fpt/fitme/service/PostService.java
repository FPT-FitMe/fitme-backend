package com.fpt.fitme.service;

import com.fpt.fitme.dto.post.DisablePostDTO;
import com.fpt.fitme.dto.post.PostDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.post.Post;
import com.fpt.fitme.entity.workout.CoachProfile;
import com.fpt.fitme.repository.CoachProfileRepository;
import com.fpt.fitme.repository.PostRepository;
import com.fpt.fitme.util.JsonPatcherUtil;
import com.github.fge.jsonpatch.JsonPatch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FitmeUserDetailsService fitmeUserDetailsService;

    @Autowired
    private CoachProfileRepository coachProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PostDTO> getListPost() {
        List<PostDTO> result = new ArrayList<>();
        postRepository.findAll().forEach(post -> {
            if (post.getIsActive()) {
                PostDTO dto = modelMapper.map(post, PostDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public List<PostDTO> getListPostByCoach(long coachID) throws Exception {
        Optional<CoachProfile> coachProfile = coachProfileRepository.findById(coachID);
        if (!coachProfile.isPresent()) throw new Exception("coachID not found!");

        List<PostDTO> result = new ArrayList<>();
        postRepository.getPostsByCoachProfile(coachProfile.get()).forEach(post -> {
            if (post.getIsActive()) {
                PostDTO dto = modelMapper.map(post, PostDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public PostDTO getPostByID(long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent() && postOptional.get().getIsActive())
            return modelMapper.map(postOptional.get(), PostDTO.class);
        return null;
    }

    public PostDTO createPost(Post post) throws Exception {
        AppUser appUser = fitmeUserDetailsService.getUserByAuthorization();

        Optional<CoachProfile> coachProfile = coachProfileRepository.findById(post.getCoachProfile().getCoachID());
        if (!coachProfile.isPresent()) throw new Exception("coachID not found!");

        post.setCoachProfile(coachProfile.get());
        post.setCreator(appUser);
        post.setIsActive(true);

        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    public PostDTO patchPost(Long id, JsonPatch patch) throws Exception {
        Optional<Post> currentPost = postRepository.findById(id);

        if (!(currentPost.isPresent() && currentPost.get().getIsActive()))
            throw new Exception("postID not found!");

        Post postPatched = (Post) JsonPatcherUtil.applyPatch(patch, currentPost.get());
        postRepository.save(postPatched);
        return modelMapper.map(postPatched, PostDTO.class);
    }

    public DisablePostDTO disablePost(Long id, JsonPatch patch) throws Exception {
        Optional<Post> currentPost = postRepository.findById(id);

        if (!(currentPost.isPresent() && currentPost.get().getIsActive()))
            throw new Exception("postID not found!");

        Post postPatched = (Post) JsonPatcherUtil.applyPatch(patch, currentPost.get());
        postRepository.save(postPatched);

        return modelMapper.map(postPatched, DisablePostDTO.class);
    }

    public PostDTO updatePost(Long id, Post post) throws Exception {
        Optional<Post> optionalPost = postRepository.findById(id);
        Optional<CoachProfile> coachProfile = coachProfileRepository.findById(post.getCoachProfile().getCoachID());

        if (!(coachProfile.isPresent() && coachProfile.get().getIsActive())) throw new Exception("coachID not found!");

        if (!(optionalPost.isPresent() && optionalPost.get().getIsActive())) throw new Exception("postID not found!");
        Post postToUpdate = optionalPost.get();
        postToUpdate.setName(post.getName());
        postToUpdate.setReadingTime(post.getReadingTime());
        postToUpdate.setImageUrl(post.getImageUrl());
        postToUpdate.setContentHeader(post.getContentHeader());
        postToUpdate.setContentBody(post.getContentBody());
        postToUpdate.setCoachProfile(coachProfile.get());
        postRepository.save(postToUpdate);

        return modelMapper.map(postToUpdate, PostDTO.class);

    }
}