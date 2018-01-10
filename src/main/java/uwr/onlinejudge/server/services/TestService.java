package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.models.form.TestForm;

public interface TestService {
    Test getTest(Long id);

    void save(TestForm testForm);

    void deleteTest(Long id);
}
