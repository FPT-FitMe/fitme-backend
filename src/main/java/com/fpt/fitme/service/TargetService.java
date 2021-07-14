package com.fpt.fitme.service;

import com.fpt.fitme.dto.target.TargetWeightDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.target.Target;
import com.fpt.fitme.repository.TargetRepository;
import com.fpt.fitme.repository.WeightLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TargetService {

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private WeightLogRepository weightLogRepository;

    public TargetWeightDTO getCurrentTargetWeight(AppUser trainee) throws Exception{
        Target currentTarget = targetRepository.getTargetByTraineeAndHasFinished(trainee, false);

        if (currentTarget == null) {
            throw new Exception("No target found");
        }
        float currentWeight = weightLogRepository.findTop1ByTraineeOrderByCreatedAtDesc(trainee).getValue();
        float targetWeight = currentTarget.getTargetBMI() * (float) Math.pow(trainee.getHeight() / 100, 2);
        TargetWeightDTO result = new TargetWeightDTO();
        result.setCurrentWeight(currentWeight);
        result.setTargetWeight(targetWeight);
        return result;
    }
}
