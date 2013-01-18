package com.tfnico.examples.bonnagile.spock;

public interface DutyRepository {
    Case findById(String caseId);
    String save(Case caze);
}
