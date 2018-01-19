package uwr.onlinejudge.server.util;

import org.springframework.stereotype.Component;
import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Test;

@Component
public class AnswerCheckerImpl implements AnswerChecker {

    @Override
    public TestState check(CompileResult compileResult, Test test) {
        if (compileResult.getErrors().isEmpty()) {
            if (compileResult.getOutput().replace("\n", "").compareTo(test.getExpectedAnswer()) == 0) {
                return TestState.OK;
            } else {
                return TestState.WA;
            }
        } else {
            return TestState.RE;
        }
    }
}
