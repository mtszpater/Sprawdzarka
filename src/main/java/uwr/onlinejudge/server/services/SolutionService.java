package uwr.onlinejudge.server.services;

import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.repositories.SolutionRepository;

@Service
public class SolutionService implements SolutionServiceImpl {
    SolutionRepository solutionRepository;

    public SolutionService(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    public Solution getSolution(long solutionId) {
       return solutionRepository.getOne(solutionId);
    };
}
