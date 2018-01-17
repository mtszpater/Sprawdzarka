package uwr.onlinejudge.server.util;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uwr.onlinejudge.server.models.CodeToCompile;
import uwr.onlinejudge.server.models.CompileResult;

public class CompileSenderImpl implements CompileSender {
    private static final String COMPILE_URL = "http://156.17.4.48/compile";

    @Override
    public CompileResult send(CodeToCompile codeToCompile) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<?> request = new HttpEntity<>(codeToCompile, headers);

        ResponseEntity<CompileResult> response = new RestTemplate().postForEntity(COMPILE_URL, request, CompileResult.class);
        return response.getBody();
    }


}
