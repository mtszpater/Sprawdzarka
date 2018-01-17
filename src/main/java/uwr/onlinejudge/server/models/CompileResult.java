package uwr.onlinejudge.server.models;

public class CompileResult {
    private String output;
    private int langid;
    private String code;
    private String errors;
    private String time;

    public CompileResult() {
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getLangid() {
        return langid;
    }

    public void setLangid(int langid) {
        this.langid = langid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CompileResult{" +
                "output='" + output + '\'' +
                ", langid=" + langid +
                ", code='" + code + '\'' +
                ", errors='" + errors + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

