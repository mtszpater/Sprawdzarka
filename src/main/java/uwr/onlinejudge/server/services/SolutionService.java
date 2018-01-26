package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.form.SolutionForm;

public interface SolutionService {

    Solution save(SolutionForm solutionForm);

    Solution getSolution(Long id);

    Solution save(Solution solution);
}
