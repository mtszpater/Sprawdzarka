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
    CompileResultTimeConverter compileResultTimeConverter;

    @Autowired
    public ScoreCalculatorImpl(AnswerChecker answerChecker, CompileResultTimeConverter compileResultTimeConverter) {
        this.answerChecker = answerChecker;
        this.compileResultTimeConverter = compileResultTimeConverter;
    }

    public Score calculate(Solution solution, Test test, CompileResult compileResult) {

        int executionTime = compileResultTimeConverter.convert(compileResult.getTime());

        Score score = new Score(solution, test, compileResult.getOutput(), executionTime);

        TestState testState = answerChecker.check(compileResult, test);

        score.setState(testState);
        if (score.getState().compareTo(TestState.OK) == 0) {
            score.setPoint(test.getPoint());
        }

        return score;
    }
}
