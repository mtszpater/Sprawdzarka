package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.services.CompileService;

import java.util.Collection;

@RestController
public class CompileController {

    CompileService compileService;

    @Autowired
    public CompileController(CompileService compileService) {
        this.compileService = compileService;
    }

    @GetMapping("/api/compile/{solutionId}")
    public Collection<CompileResult> getCompileResult(@PathVariable("solutionId") Long solutionId) {
        return compileService.compileSolution(solutionId);
    }

}
