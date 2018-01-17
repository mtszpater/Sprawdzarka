package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uwr.onlinejudge.server.models.*;
import uwr.onlinejudge.server.services.SolutionService;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.util.CompileSender;

import java.util.ArrayList;

@RestController
public class CompileController {
    CompileSender compileSender;
    SolutionService solutionService;
    TaskService taskService;

    @Autowired
    public CompileController(CompileSender compileSender, SolutionService solutionService, TaskService taskService) {
        this.compileSender = compileSender;
        this.solutionService = solutionService;
        this.taskService = taskService;
    }

    @GetMapping("/api/compile/{solutionId}/{testOrder}")
    public CompileResult getCompileResult(@PathVariable("solutionId") Long solutionId,
                                          @PathVariable("testOrder") int testOrder) {

        Solution solution = solutionService.getSolution(solutionId);
        Task task = solution.getTask();
        ArrayList<Test> tests = (ArrayList<Test>) taskService.getTests(task);

        if (testOrder > tests.size())
            return null;

        CodeToCompile codeToCompile = new CodeToCompile("0", solution.getSolution(), tests.get(testOrder).getInputArgument());
        System.out.println(codeToCompile);

        return compileSender.send(codeToCompile);
    }

}
