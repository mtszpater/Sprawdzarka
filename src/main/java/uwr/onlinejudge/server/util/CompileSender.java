package uwr.onlinejudge.server.util;

import uwr.onlinejudge.server.models.CodeToCompile;
import uwr.onlinejudge.server.models.CompileResult;

public interface CompileSender {
    CompileResult send(CodeToCompile codeToCompile);
}
