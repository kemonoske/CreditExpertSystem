package com.systems.expert.core;

import com.systems.expert.core.data.Client;

import java.util.HashMap;

/**
 * Created by Kage on 2/23/14.
 */
public class FinancialInvestigator implements ExpertSystem<Client> {

    private Client data;

    private HashMap<String, Boolean> decisionConditions;


    @Override
    public void consumeData(Client data) {

        decisionConditions = new HashMap<String, Boolean>();

        decisionConditions.put("Adeverința de salariu", data.isSalaryInvoicePresent());
        decisionConditions.put("Copia carnetului de muncă", data.isWorkCardCopyPresent());
        decisionConditions.put("Nu are datorii față de bancă", !data.isPossessingDebts());
        decisionConditions.put("Stagiu curent de muncă peste 6 luni", data.getMonthWorking() >= 6 ? true : false);

    }

    @Override
    public Decision takeDecision() {

        for (Boolean decision : decisionConditions.values())
            if (!decision)
                return Decision.NEGATIVE;

        return Decision.POSITIVE;
    }

    @Override
    public HashMap<String, Boolean> getDecisionConditions() {
        return decisionConditions;
    }
}
