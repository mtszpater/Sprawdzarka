package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.Score;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.models.form.SolutionForm;

public interface SolutionService {

    Solution getSolution(long solutionId);

    void save(SolutionForm solutionForm);

    Score findScoreBySolutionAndTest(Solution solution, Test test);

    void save(Score score);
}
