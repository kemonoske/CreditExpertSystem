package com.systems.expert.core;

import com.systems.expert.core.data.Nationality;
import com.systems.expert.core.data.Person;

import java.util.HashMap;

/**
 * Created by Kage on 2/20/14.
 */
public class PersonalInvestigator implements ExpertSystem<Person> {

    private Person data;

    private HashMap<String, Boolean> decisionConditions;


    @Override
    public void consumeData(Person data) {

        decisionConditions = new HashMap<String, Boolean>();

        decisionConditions.put("Vîrsta peste 18 ani", (data.getAge() > 18)?true:false);
        decisionConditions.put("Copia buletinului de identitate", data.isIdentityCardCopyPresent());
        decisionConditions.put("Catățean al Republicii Moldova", data.getNationality() == Nationality.MD?true:false);

    }

    @Override
    public Decision takeDecision() {

        for(Boolean decision : decisionConditions.values())
            if(!decision)
                return Decision.NEGATIVE;

        return Decision.POSITIVE;
    }

    @Override
    public HashMap<String, Boolean> getDecisionConditions() {
        return decisionConditions;
    }
}
