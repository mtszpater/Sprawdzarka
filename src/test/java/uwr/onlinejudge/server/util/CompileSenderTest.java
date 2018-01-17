package uwr.onlinejudge.server.util;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uwr.onlinejudge.server.models.CodeToCompile;
import uwr.onlinejudge.server.models.CompileResult;

public class CompileSenderTest {

    @Test
    public void Test1(){
        CompileSender compileSender = new CompileSenderImpl();
        CodeToCompile codeToCompile = new CodeToCompile("0", "print('Hello2')", "");
        System.out.println(compileSender.send(codeToCompile).getOutput());
    }
}