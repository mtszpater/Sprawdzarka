package uwr.onlinejudge.server.util.compiler;

import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Score;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.Test;

public interface ScoreCalculator {
    Score calculate(Solution solution, Test test, CompileResult compileResult);
}
