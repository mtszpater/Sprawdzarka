package uwr.onlinejudge.server.controllers;

import org.springframework.web.bind.annotation.*;
import uwr.onlinejudge.server.models.CompileResult;

@RestController
public class CompileController {

    @GetMapping("/api/compile/{solutionId}")
    public CompileResult getCompileResult(@PathVariable("solutionId") Long solutionId) {
        return new CompileResult();
    }

}
