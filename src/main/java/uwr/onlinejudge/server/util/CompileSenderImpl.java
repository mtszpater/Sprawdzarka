package uwr.onlinejudge.server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uwr.onlinejudge.server.models.CodeToCompile;
import uwr.onlinejudge.server.models.CompileResult;

@Component
public class CompileSenderImpl implements CompileSender {
    private static final String COMPILE_URL = "http://156.17.4.48/compile";
    private final RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders;

    @Autowired
    public CompileSenderImpl() {
        this.httpHeaders = new HttpHeaders();
    }

    @Override
    public CompileResult send(CodeToCompile codeToCompile) {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(codeToCompile, httpHeaders);

        ResponseEntity<CompileResult> response = restTemplate.postForEntity(COMPILE_URL, request, CompileResult.class);
        return response.getBody();
    }


}
