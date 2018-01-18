package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uwr.onlinejudge.server.models.*;
import uwr.onlinejudge.server.services.SolutionService;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.util.CompileSender;
import uwr.onlinejudge.server.util.TestState;

import java.util.ArrayList;
import java.util.Collection;

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

    @GetMapping("/api/compile/{solutionId}")
    public Collection<CompileResult> getCompileResult(@PathVariable("solutionId") Long solutionId) {

        Solution solution = solutionService.getSolution(solutionId);
        ArrayList<Test> tests = (ArrayList<Test>) taskService.getTests(solution.getTask());
        Collection<CompileResult> compileResults = new ArrayList<>();


        for (Test test : tests) {
            if (solutionService.findScoreBySolutionAndTest(solution, test) != null) {
                continue;
            }

            CodeToCompile codeToCompile = new CodeToCompile("" + solution.getLanguage().getId(), solution.getSolution(), test.getInputArgument());
            CompileResult compileResult = null;
            try {
                compileResult = compileSender.send(codeToCompile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            compileResults.add(compileResult);

            Score score = new Score();
            //score.setExecutionTime(compileResult.getTime());
            score.setSolution(solution);
            score.setTest(test);

            if (compileResult.getErrors().isEmpty()) {
                if (compileResult.getOutput().replace("\n", "").compareTo(test.getExpectedAnswer()) == 0) {
                    score.setPoint(test.getPoint());
                    score.setState(TestState.OK);
                } else {
                    score.setState(TestState.WA);
                }
            } else {
                score.setState(TestState.RE);
            }

            score.setTestResult(compileResult.getOutput());
            solutionService.save(score);
        }

        return compileResults;
    }
}
