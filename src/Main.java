public class Main {
    public static void main(String[] args) {
        ProcessManager pm = ProcessManager.getInstance();
        Process rootProcess = pm.createRoot();
        rootProcess.fork(ProcessType.kernel);
    }
}