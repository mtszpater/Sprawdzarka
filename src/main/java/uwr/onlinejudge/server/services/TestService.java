package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.models.form.TestForm;

public interface TestService {
    Test getTest(Long id);

    Test save(TestForm testForm);

    Test save(Test test);

    void deleteTest(Long id);
}
