package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.services.CompileService;
import uwr.onlinejudge.server.services.SolutionService;

import java.util.Collection;

@RestController
public class CompileController {

    private CompileService compileService;
    private SolutionService solutionService;

    @Autowired
    public CompileController(CompileService compileService, SolutionService solutionService) {
        this.compileService = compileService;
        this.solutionService = solutionService;
    }

    @GetMapping("/api/compile/{solutionId}")
    public Collection<CompileResult> getCompileResult(@PathVariable("solutionId") Long solutionId) {
        Collection<CompileResult> compileResults = compileService.compileSolution(solutionId);

        Solution solution = solutionService.getSolution(solutionId);
        solution.setCompiled(true);
        solutionService.save(solution);

        return compileResults;
    }

}
