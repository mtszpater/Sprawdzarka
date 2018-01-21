package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.models.form.TestForm;
import uwr.onlinejudge.server.repositories.TestRepository;

import javax.transaction.Transactional;

@Service
public class TestServiceImpl implements TestService {
    private TestRepository testRepository;
    private ObjectMapper objectMapper;
    private TaskService taskService;

    @Autowired
    public TestServiceImpl(TestRepository testRepository, ObjectMapper objectMapper, TaskService taskService) {
        this.testRepository = testRepository;
        this.objectMapper = objectMapper;
        this.taskService = taskService;
    }

    @Override
    public Test getTest(Long id) {
        return testRepository.findOne(id);
    }

    @Override
    public void save(TestForm testForm) {
        testRepository.save(objectMapper.convertValue(testForm, Test.class));
    }

    @Override
    @Transactional
    public void deleteTest(Long id) {
        taskService.deleteScores(getTest(id));
        testRepository.delete(id);
    }
}
