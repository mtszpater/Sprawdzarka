package uwr.onlinejudge.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import uwr.onlinejudge.server.models.*;
import uwr.onlinejudge.server.repositories.ScoreRepository;
import uwr.onlinejudge.server.repositories.SolutionRepository;
import uwr.onlinejudge.server.repositories.TestRepository;
import uwr.onlinejudge.server.util.compiler.CompileSender;
import uwr.onlinejudge.server.util.compiler.ScoreCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompileServiceImpl implements CompileService {
    private CompileSender compileSender;
    private ScoreCalculator scoreCalculator;
    private SolutionRepository solutionRepository;
    private ScoreRepository scoreRepository;
    private TestRepository testRepository;

    @Autowired
    public CompileServiceImpl(CompileSender compileSender, ScoreCalculator scoreCalculator, SolutionRepository solutionRepository, ScoreRepository scoreRepository, TestRepository testRepository) {
        this.compileSender = compileSender;
        this.scoreCalculator = scoreCalculator;
        this.solutionRepository = solutionRepository;
        this.scoreRepository = scoreRepository;
        this.testRepository = testRepository;
    }

    @Override
    public void compileLastSolutions(Test test) throws ResourceAccessException {
        List<Registration> registrations = test.getTask().getTaskList().getGroup().getRegistrations();

        List<User> users = registrations.stream().map(Registration::getUser).collect(Collectors.toList());
        List<Solution> lastSolutions = users.stream()
                .map(user -> solutionRepository.findFirstByUserOrderByDateOfSendingDesc(user))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        lastSolutions.forEach(solution -> {
                    CodeToCompile codeToCompile = new CodeToCompile("" + solution.getLanguage().getId(), solution.getSolution(), test.getInputArgument());
                    CompileResult compileResult = compileSender.send(codeToCompile);
                    scoreRepository.save(scoreCalculator.calculate(solution, test, compileResult));
                }
        );
    }

    @Override
    public Collection<CompileResult> compileSolution(Long solutionId) {
        Solution solution = solutionRepository.findOne(solutionId);
        ArrayList<Test> tests = (ArrayList<Test>) testRepository.findByTask(solution.getTask());
        Collection<CompileResult> compileResults = new ArrayList<>();
        CodeToCompile codeToCompile;
        CompileResult compileResult;
        for (Test test : tests) {
            boolean solutionCompiled = scoreRepository.findBySolutionAndTest(solution, test) != null;

            if (solutionCompiled) {
                continue;
            }

            codeToCompile = new CodeToCompile("" + solution.getLanguage().getId(), solution.getSolution(), test.getInputArgument());
            compileResult = compileSender.send(codeToCompile);
            compileResults.add(compileResult);

            scoreRepository.save(scoreCalculator.calculate(solution, test, compileResult));
        }

        return compileResults;
    }
}
