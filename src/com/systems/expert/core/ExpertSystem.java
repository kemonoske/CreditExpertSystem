package com.systems.expert.core;

import java.util.HashMap;

/**
 * Created by Kage on 2/20/14.
 */
public interface ExpertSystem<T> {

    public void consumeData(T data);

    public Decision takeDecision();

    public HashMap<String, Boolean> getDecisionConditions();

}
