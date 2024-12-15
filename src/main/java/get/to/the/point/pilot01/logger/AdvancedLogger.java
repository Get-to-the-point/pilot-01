package get.to.the.point.pilot01.logger;

// 로거2: 구분선 포함 로깅
public class AdvancedLogger {
    public void log(String message) {
        System.out.println("============================");
        System.out.println("[LOG]: " + message);
        System.out.println("============================");
    }
}