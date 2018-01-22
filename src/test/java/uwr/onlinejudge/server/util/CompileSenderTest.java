package uwr.onlinejudge.server.util;

import org.junit.Assert;
import org.junit.Test;
import uwr.onlinejudge.server.models.CodeToCompile;
import uwr.onlinejudge.server.models.CompileResult;
import uwr.onlinejudge.server.util.compiler.CompileSender;
import uwr.onlinejudge.server.util.compiler.CompileSenderImpl;

public class CompileSenderTest {

    @Test
    public void Test1(){
        CompileSender compileSender = new CompileSenderImpl();
        CodeToCompile codeToCompile = new CodeToCompile("0", "print('Hello')", "");
        CompileResult compileResult = compileSender.send(codeToCompile);
        Assert.assertEquals(compileResult.getOutput().compareTo("Hello"), 1);
    }
}