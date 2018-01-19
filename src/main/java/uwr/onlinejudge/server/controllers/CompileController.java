package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uwr.onlinejudge.server.models.CodeToCompile;
import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.services.SolutionService;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.util.CompileSender;
import uwr.onlinejudge.server.util.ScoreCalculator;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class CompileController {
    CompileSender compileSender;
    SolutionService solutionService;
    TaskService taskService;
    ScoreCalculator scoreCalculator;

    @Autowired
    public CompileController(CompileSender compileSender, SolutionService solutionService, TaskService taskService, ScoreCalculator scoreCalculator) {
        this.compileSender = compileSender;
        this.solutionService = solutionService;
        this.taskService = taskService;
        this.scoreCalculator = scoreCalculator;
    }

    @GetMapping("/api/compile/{solutionId}")
    public Collection<CompileResult> getCompileResult(@PathVariable("solutionId") Long solutionId) {

        Solution solution = solutionService.getSolution(solutionId);
        ArrayList<Test> tests = (ArrayList<Test>) taskService.getTests(solution.getTask());
        Collection<CompileResult> compileResults = new ArrayList<>();
        CodeToCompile codeToCompile;
        CompileResult compileResult;

        for (Test test : tests) {
            boolean solutionCompiled = solutionService.findScoreBySolutionAndTest(solution, test) != null;

            if (solutionCompiled) {
                continue;
            }

            codeToCompile = new CodeToCompile("" + solution.getLanguage().getId(), solution.getSolution(), test.getInputArgument());
            compileResult = compileSender.send(codeToCompile);
            compileResults.add(compileResult);

            solutionService.save(scoreCalculator.calculate(solution, test, compileResult));
        }
        return compileResults;
    }

}
