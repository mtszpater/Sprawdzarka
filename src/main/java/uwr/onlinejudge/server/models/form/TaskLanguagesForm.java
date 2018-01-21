package uwr.onlinejudge.server.models.form;

import uwr.onlinejudge.server.util.Languages;

import java.util.Map;

public class TaskLanguagesForm {

    private Map<Languages, Boolean> languagesMap;

    public TaskLanguagesForm(Map<Languages, Boolean> allPossibleLanguages) {
        this.languagesMap = allPossibleLanguages;
    }

    public TaskLanguagesForm() {
    }

    public Map<Languages, Boolean> getLanguagesMap() {
        return languagesMap;
    }
}
