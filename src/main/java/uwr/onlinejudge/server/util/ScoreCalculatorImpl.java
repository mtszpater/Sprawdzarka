package uwr.onlinejudge.server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Score;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.Test;

@Component
public class ScoreCalculatorImpl implements ScoreCalculator {
    AnswerChecker answerChecker;

    @Autowired
    public ScoreCalculatorImpl(AnswerChecker answerChecker) {
        this.answerChecker = answerChecker;
    }

    public Score calculate(Solution solution, Test test, CompileResult compileResult) {

        String[] timeSplit = compileResult.getTime().split("\\.");
        Score score = new Score(solution, test, compileResult.getOutput(), Integer.parseInt(timeSplit[1].replace("\n", "")));

        TestState testState = answerChecker.check(compileResult, test);

        score.setState(testState);
        if (score.getState().compareTo(TestState.OK) == 0) {
            score.setPoint(test.getPoint());
        }

        return score;
    }
}
