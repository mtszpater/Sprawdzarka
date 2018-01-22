package uwr.onlinejudge.server.services;

import org.springframework.web.client.ResourceAccessException;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.Test;

public interface CompileService {

    void compileLastSolutions(Task task, Test test) throws ResourceAccessException;
}
