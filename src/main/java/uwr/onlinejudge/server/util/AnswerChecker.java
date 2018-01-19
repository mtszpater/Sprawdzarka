package uwr.onlinejudge.server.util;

import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Test;

public interface AnswerChecker {
    TestState check(CompileResult compileResult, Test test);
}
