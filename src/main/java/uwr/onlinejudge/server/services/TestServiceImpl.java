package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.models.form.TestForm;
import uwr.onlinejudge.server.repositories.TestRepository;

@Service
public class TestServiceImpl implements TestService {
    private TestRepository testRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public TestServiceImpl(TestRepository testRepository, ObjectMapper objectMapper) {
        this.testRepository = testRepository;
        this.objectMapper = objectMapper;
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
    public void deleteTest(Long id) {
        testRepository.delete(id);
    }
}
