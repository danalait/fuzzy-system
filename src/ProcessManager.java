import java.util.HashMap;

/* this is kind of the PID and some functions bundled
    will use singleton pattern, no conflict over pid (always unique)
    class ProcessManager {
        -Map<Integer,Process> processes  // done
        -int nextPid                     // done
        +getInstance()                   // done
        +getNextAvailablePid()           // done
        +createProcess()                 // done
        +getProcess()                   // done
        +removeProcess()
    }
*/
public class ProcessManager {
    private static ProcessManager instance;
    private final HashMap<Integer,Process> processes = new  HashMap<>();
    private int nextPid = 2; // reserving 0 for entry process
    private boolean rootCreated = false;

    private ProcessManager(){}

    // TODO: Exception for Root already existing
    public Process createRoot(){
        if (!rootCreated){
            throw new IllegalStateException("Root already exists");
        }
        Process rootProcess = createProcess(1, 0, ProcessType.kernel);
        this.processes.put(1, rootProcess);
        rootCreated = true;
        return rootProcess;
    };

    public Process createChild(Process parentProcess, ProcessType type) {
        int ppid = parentProcess.getPid();
        int pid = getNextAvailablePid();
        Process childProcess = createProcess(pid, ppid, type);
        this.processes.put(pid, childProcess);
        return childProcess;
    };

    private Process createProcess(int pid, int ppid, ProcessType type) {
        if (type == ProcessType.user){
            return new UserProcess(pid, ppid);
        }
        if (type == ProcessType.kernel){
            return new KernelProcess(pid, ppid);
        }
        throw new ProcessTypeException("Process Type doesn't exist");
    }

    public static ProcessManager getInstance(){
        return instance;
    }

    public Process getProcess(int pid){
        return this.processes.get(pid);
    }

    public int getNextAvailablePid(){
        return this.nextPid++;
    }


}
