package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uwr.onlinejudge.server.models.CodeToCompile;
import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.services.SolutionService;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.util.CompileSender;

@RestController
public class CompileController {
    CompileSender compileSender;
    SolutionService solutionService;

    @Autowired
    public CompileController(CompileSender compileSender, SolutionService solutionService) {
        this.compileSender = compileSender;
        this.solutionService = solutionService;
    }

    @GetMapping("/api/compile/{solutionId}")
    public CompileResult getCompileResult(@PathVariable("solutionId") Long solutionId) {
        Solution solution = solutionService.getSolution(solutionId);
        CodeToCompile codeToCompile = new CodeToCompile("0", solution.getSolution(), "");
        return compileSender.send(codeToCompile);
    }

}
