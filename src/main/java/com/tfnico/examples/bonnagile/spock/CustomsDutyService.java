package com.tfnico.examples.bonnagile.spock;

class CustomsDutyService {

    private final DutyRepository repository;

    CustomsDutyService(DutyRepository repository) {
        this.repository = repository;
    }

    public Case findCase(String caseId) {
        return repository.findById(caseId);
    }

    public void saveCase(String caseId, int amountToPay) {
        Case caze = new Case(amountToPay);
        this.repository.save(caze);
    }

    public String registerCase(int value, CaseType type) {
        Case caze = new Case(0);
        return repository.save(caze);
    }
}