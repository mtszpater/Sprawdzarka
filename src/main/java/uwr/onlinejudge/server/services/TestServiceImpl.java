package uwr.onlinejudge.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.repositories.TestRepository;

@Service
public class TestServiceImpl implements TestService {
    TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Test getTest(Long id) {
        return testRepository.findOne(id);
    }
}
