package com.ssutherlanddee;

public class BranchPredictorFactory {

    public BranchPredictorFactory() {}

    public BranchPredictor create(Integer type) {
        switch (type) {
            case 0:
                return new FixedBranchPredictor(true);
            case 1:
                return new FixedBranchPredictor(false);
            case 2:
                return new StaticBranchPredictor();
            case 3:
                return new DynamicBranchPredictor(1);
            case 4:
                return new DynamicBranchPredictor(3);
            default:
                return new StaticBranchPredictor();
        }
    }
}