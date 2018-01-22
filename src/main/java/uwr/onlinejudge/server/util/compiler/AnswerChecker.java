package uwr.onlinejudge.server.util.compiler;

import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.util.TestState;

public interface AnswerChecker {
    TestState check(CompileResult compileResult, Test test);
}
