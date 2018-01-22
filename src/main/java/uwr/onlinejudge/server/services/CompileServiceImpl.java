package uwr.onlinejudge.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import uwr.onlinejudge.server.models.*;
import uwr.onlinejudge.server.repositories.RegistrationRepository;
import uwr.onlinejudge.server.repositories.ScoreRepository;
import uwr.onlinejudge.server.repositories.SolutionRepository;
import uwr.onlinejudge.server.util.CompileSender;
import uwr.onlinejudge.server.util.ScoreCalculator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompileServiceImpl implements CompileService {
    private CompileSender compileSender;
    private ScoreCalculator scoreCalculator;
    private RegistrationRepository registrationRepository;
    private SolutionRepository solutionRepository;
    private ScoreRepository scoreRepository;

    @Autowired
    public CompileServiceImpl(CompileSender compileSender, ScoreCalculator scoreCalculator, RegistrationRepository registrationRepository, SolutionRepository solutionRepository, ScoreRepository scoreRepository) {
        this.compileSender = compileSender;
        this.scoreCalculator = scoreCalculator;
        this.registrationRepository = registrationRepository;
        this.solutionRepository = solutionRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void compileLastSolutions(Task task, Test test) throws ResourceAccessException {
        List<Registration> registrations = registrationRepository.findByGroup(task.getTaskList().getGroup());
        List<User> users = registrations.stream().map(registration -> registration.getUser()).collect(Collectors.toList());
        List<Solution> lastSolutions = users.stream().map(user -> solutionRepository.findFirstByUserOrderByDateOfSendingDesc(user)).collect(Collectors.toList());
        lastSolutions.forEach(solution -> {
                    CodeToCompile codeToCompile = new CodeToCompile("" + solution.getLanguage().getId(), solution.getSolution(), test.getInputArgument());
                    CompileResult compileResult = compileSender.send(codeToCompile);
                    scoreRepository.save(scoreCalculator.calculate(solution, test, compileResult));
                }
        );
    }


}
