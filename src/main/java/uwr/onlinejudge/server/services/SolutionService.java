package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.form.SolutionForm;
import uwr.onlinejudge.server.repositories.SolutionRepository;

@Service
public class SolutionService implements SolutionServiceImpl {
    SolutionRepository solutionRepository;
    ObjectMapper objectMapper;

    public SolutionService(SolutionRepository solutionRepository, ObjectMapper objectMapper) {
        this.solutionRepository = solutionRepository;
        this.objectMapper = objectMapper;
    }

    public Solution getSolution(long solutionId) {
       return solutionRepository.getOne(solutionId);
    };

    public void save(SolutionForm solutionForm) {
        solutionRepository.save(objectMapper.convertValue(solutionForm, Solution.class));
    }
}
