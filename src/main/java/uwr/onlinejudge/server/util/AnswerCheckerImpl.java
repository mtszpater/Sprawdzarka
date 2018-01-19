package uwr.onlinejudge.server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Test;

@Component
public class AnswerCheckerImpl implements AnswerChecker {
    CompileResultTimeConverter compileResultTimeConverter;

    @Autowired
    public AnswerCheckerImpl(CompileResultTimeConverter compileResultTimeConverter) {
        this.compileResultTimeConverter = compileResultTimeConverter;
    }

    @Override
    public TestState check(CompileResult compileResult, Test test) {
        if (compileResult.getErrors().isEmpty()) {

            int executionTime = compileResultTimeConverter.convert(compileResult.getTime());
            if (executionTime > test.getTimeRequired()) {
                return TestState.TLE;
            }

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
