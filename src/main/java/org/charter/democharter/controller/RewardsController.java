package org.charter.democharter.controller;

import io.swagger.annotations.ApiOperation;
import org.charter.democharter.business.model.ResponseModel;
import org.charter.democharter.business.model.Rewards;
import org.charter.democharter.business.model.StatusTypeText;
import org.charter.democharter.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping(value = "/customer/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    @ApiOperation(value = "Retrieve Reward Points for a particular User ID")
    public ResponseModel<Rewards> getRewardsByCustomerID(@PathVariable Integer id) {
        Rewards rewards = rewardsService.getRewardsByCustomerID(id);

        return !CollectionUtils.isEmpty(rewards.getRewardPointsMap())
                ? ResponseModel.of(rewards, HttpStatus.OK.value(), StatusTypeText.SUCCESS.getValue())
                : ResponseModel.of(rewards, HttpStatus.NO_CONTENT.value(), StatusTypeText.FAILURE.getValue());
    }

    @GetMapping(value = "/all",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    @ApiOperation(value = "Retrieve Reward Points for all Users for Last Three Months Period")
    public ResponseModel<List<Rewards>> getAllCustomerRewards() {
        List<Rewards> rewardsList = rewardsService.getAllRewards();

        return !CollectionUtils.isEmpty(rewardsList)
                ? ResponseModel.of(rewardsList, HttpStatus.OK.value(), StatusTypeText.SUCCESS.getValue())
                : ResponseModel.of(rewardsList, HttpStatus.NO_CONTENT.value(), StatusTypeText.FAILURE.getValue());
    }
}
