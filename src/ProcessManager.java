import java.util.*;

public final class ProcessManager {
    private static final ProcessManager INSTANCE = new ProcessManager();

    private final Map<Integer, Process> processes = new HashMap<>();
    private int nextPid = 2;         // 1 reserved for root
    private boolean rootCreated = false;

    private ProcessManager(){}

    public static ProcessManager getInstance() { return INSTANCE; }

    // ----- creation -----
    public Process createRoot() {
        if (rootCreated) throw new IllegalStateException("Root already exists");
        Process root = new KernelProcess(1, 0);
        processes.put(1, root);
        rootCreated = true;
        return root;
    }

    public Process createChild(Process parent, ProcessType type) {
        if (parent == null) throw new IllegalArgumentException("parent is null");
        return createChild(parent.getPid(), type);
    }

    public Process createChild(int ppid, ProcessType type) {
        int pid = nextPid++;
        Process p = switch (type) {
            case user -> new UserProcess(pid, ppid);
            case kernel -> new KernelProcess(pid, ppid);
        };
        processes.put(pid, p);
        return p;
    }

    // ----- lookup / maintenance -----
    public Process get(int pid) { return processes.get(pid); }

    public List<Process> getChildren(int ppid) {
        List<Process> out = new ArrayList<>();
        for (Process p : processes.values()) if (p.getPpid() == ppid) out.add(p);
        return out;
    }

    public boolean remove(int pid) { return processes.remove(pid) != null; }

    public Collection<Process> all() { return Collections.unmodifiableCollection(processes.values()); }

    // test helper
    void resetForTests() {
        processes.clear();
        nextPid = 2;
        rootCreated = false;
    }
}
