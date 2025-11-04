public abstract class Process {
    private final int pid;
    private final int ppid;
    private final ProcessType type;
    private final int[] registers = new int[32];
    private ProcessState state = ProcessState.NEW;

    public Process(int pid, int ppid, ProcessType type ) {
        this.pid = pid;
        this.ppid = ppid;
        this.type = type;
    }

    // state save/restore
    public void saveState(int[] out) {
        if (out.length != 32) throw new IllegalArgumentException("32 regs");
        System.arraycopy(registers, 0, out, 0, 32);
    }

    public void loadState(int[] in) {
        if (in.length != 32) throw new IllegalArgumentException("32 regs");
        System.arraycopy(in, 0, registers, 0, 32);
    }


    public abstract void execute();

    // scheduling hook
    public void executeOneQuantum(int quantum) {
        // default implementation: run part of execute()
        for (int i = 0; i < quantum; i++) {
            execute();
            // update remaining burst, registers, etc.
        }
    };

    // accessors
    public int getPid() { return pid; }
    public int getPpid() { return ppid; }
    public ProcessType getType() { return type; }
    public ProcessState getState() { return state; }
    public void setState(ProcessState s) { this.state = s; }

    // optional controlled register access
    public int readReg(int idx) { return registers[idx]; }
    public void writeReg(int idx, int value) { registers[idx] = value; }

    public void print(){
        System.out.println("Process " + pid + " Type: " + type);
    }

}

