package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.form.SolutionForm;

public interface SolutionServiceImpl {

    public Solution getSolution(long solutionId);

    public void save(SolutionForm solutionForm);
}
