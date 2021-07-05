package com.fpt.fitme.service;

import com.fpt.fitme.dto.CoachProfile.CoachProfileDTO;
import com.fpt.fitme.dto.CoachProfile.DisableCoachProfileDTO;
import com.fpt.fitme.entity.workout.CoachProfile;
import com.fpt.fitme.repository.CoachProfileRepository;
import com.fpt.fitme.util.JsonPatcherUtil;
import com.github.fge.jsonpatch.JsonPatch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoachProfileService {

    @Autowired
    private CoachProfileRepository coachProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CoachProfileDTO> getListCoach() {
        List<CoachProfileDTO> result = new ArrayList<>();
        coachProfileRepository.findAll().forEach(coach -> {
            if (coach.getIsActive()) {
                CoachProfileDTO dto = modelMapper.map(coach, CoachProfileDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public CoachProfileDTO getCoachProfileByID(long id) {
        Optional<CoachProfile> coachOptional = coachProfileRepository.findById(id);
        if (coachOptional.isPresent() && coachOptional.get().getIsActive())
            return modelMapper.map(coachOptional.get(), CoachProfileDTO.class);
        return null;
    }

    public CoachProfileDTO createCoachProfile(CoachProfile coach) throws Exception {
        coach.setIsActive(true);
        CoachProfile savedCoachProfile = coachProfileRepository.save(coach);
        return modelMapper.map(savedCoachProfile, CoachProfileDTO.class);
    }

    public CoachProfileDTO patchCoachProfile(Long id, JsonPatch patch) throws Exception {
        Optional<CoachProfile> currentCoachProfile = coachProfileRepository.findById(id);

        if (!(currentCoachProfile.isPresent() && currentCoachProfile.get().getIsActive()))
            throw new Exception("coachID not found!");

        CoachProfile coachPatched = (CoachProfile) JsonPatcherUtil.applyPatch(patch, currentCoachProfile.get());
        coachProfileRepository.save(coachPatched);
        return modelMapper.map(coachPatched, CoachProfileDTO.class);
    }

    public DisableCoachProfileDTO disableCoachProfile(Long id, JsonPatch patch) throws Exception {
        Optional<CoachProfile> currentCoachProfile = coachProfileRepository.findById(id);

        if (!(currentCoachProfile.isPresent() && currentCoachProfile.get().getIsActive()))
            throw new Exception("coachID not found!");

        CoachProfile coachPatched = (CoachProfile) JsonPatcherUtil.applyPatch(patch, currentCoachProfile.get());
        coachProfileRepository.save(coachPatched);

        return modelMapper.map(coachPatched, DisableCoachProfileDTO.class);
    }

    public CoachProfileDTO updateCoachProfile(Long id, CoachProfile coach) throws Exception {
        Optional<CoachProfile> optionalCoachProfile = coachProfileRepository.findById(id);

        if (!(optionalCoachProfile.isPresent() && optionalCoachProfile.get().getIsActive())) throw new Exception("coachID not found!");
        CoachProfile coachToUpdate = optionalCoachProfile.get();
        coachToUpdate.setName(coach.getName());
        coachToUpdate.setContact(coach.getContact());
        coachToUpdate.setImageUrl(coach.getImageUrl());
        coachToUpdate.setIntroduction(coach.getIntroduction());
        coachProfileRepository.save(coachToUpdate);

        return modelMapper.map(coachToUpdate, CoachProfileDTO.class);

    }
}
