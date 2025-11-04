public class Main {
    public static void main(String[] args) {
        ProcessManager pm = ProcessManager.getInstance();
        Process root = pm.createRoot();
        Process c1 = pm.createChild(root, ProcessType.user);
        Process c2 = pm.createChild(root.getPid(), ProcessType.kernel);
        c1.execute();
        c2.execute();
    }
}