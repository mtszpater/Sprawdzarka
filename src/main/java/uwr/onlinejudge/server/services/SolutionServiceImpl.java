package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.form.SolutionForm;
import uwr.onlinejudge.server.repositories.ScoreRepository;
import uwr.onlinejudge.server.repositories.SolutionRepository;

@Service
public class SolutionServiceImpl implements SolutionService {
    SolutionRepository solutionRepository;
    ObjectMapper objectMapper;
    ScoreRepository scoreRepository;

    @Autowired
    public SolutionServiceImpl(SolutionRepository solutionRepository, ObjectMapper objectMapper, ScoreRepository scoreRepository) {
        this.solutionRepository = solutionRepository;
        this.objectMapper = objectMapper;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Solution save(SolutionForm solutionForm) {
        return solutionRepository.save(objectMapper.convertValue(solutionForm, Solution.class));
    }

    @Override
    public Solution getSolution(Long id) {
        return solutionRepository.findOne(id);
    }

    @Override
    public Solution save(Solution solution) {
        return solutionRepository.save(solution);
    }
}
