package uwr.onlinejudge.server.services;

import org.springframework.web.client.ResourceAccessException;
import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Test;

import java.util.Collection;

public interface CompileService {

    void compileLastSolutions(Test test) throws ResourceAccessException;

    Collection<CompileResult> compileSolution(Long solutionId);
}
