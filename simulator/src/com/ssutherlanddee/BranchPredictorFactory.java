package com.ssutherlanddee;

import java.util.Optional;

public class BranchPredictorFactory {
    public enum PredictorType {NONE, FIXEDT, FIXEDNT, STATIC, DYNAMIC1, DYNAMIC3}

    public BranchPredictorFactory() {}

    public BranchPredictor create(PredictorType type) {
        switch (type) {
            case FIXEDT:
                return new FixedBranchPredictor(true);
            case FIXEDNT:
                return new FixedBranchPredictor(false);
            case STATIC:
                return new StaticBranchPredictor();
            case DYNAMIC1:
                return new DynamicBranchPredictor(1);
            case DYNAMIC3:
                return new DynamicBranchPredictor(3);
            case NONE:
                return new BranchPredictor(){
                    @Override
                    public Optional<Pair<Integer, Boolean>> predict(String encodedInstruction, Integer PC) {
                        return Optional.empty();
                    }
                };
        }
        return new DynamicBranchPredictor(3);
    }
}