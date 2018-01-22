package uwr.onlinejudge.server.util.compiler;

import org.springframework.stereotype.Component;

@Component
public class CompileResultTimeConverterImpl implements CompileResultTimeConverter {
    @Override
    public int convert(String time) {
        String[] timeSplit = time.split("\\.");
        String firstNumber = timeSplit[0].replace(" ", "");
        int numb = 0;
        if (!firstNumber.isEmpty()) {
            numb = Integer.parseInt(firstNumber);
        }
        return numb * 100 + Integer.parseInt(timeSplit[1].replace("\n", ""));
    }
}
