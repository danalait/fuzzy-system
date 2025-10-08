import java.util.HashMap;

public abstract class Process {
    private final int pid;
    private final int ppid;
    private HashMap<Integer,Integer> registers;
    private final ProcessType type;

    public Process(int pid, int ppid, String type) {
        this.pid = pid;
        this.ppid = ppid;
        this.type = ProcessType.valueOf(type);
        registers = new HashMap<>();
        for (int i=0; i<32; i++){
            registers.put(i, 0);
        }
    }

    public Process fork(ProcessType childType) {
        return ProcessManager.getInstance().createChild(this, childType);
    }

    public void saveState(HashMap<Integer,Integer> map) {
        registers.clear();
        registers.putAll(map);
    }

    public void loadState(HashMap<Integer,Integer> state){
        state.putAll(registers);
    }

    public abstract void execute();

    // accessor/modifiers

    public int getPid() {
        return pid;
    }

    public int getPpid() {
        return ppid;
    }

    public HashMap<Integer, Integer> getRegisters() {
        return registers;
    }

    // printing stuff

    public void printRegisters() {
        for (Integer key : registers.keySet()) {
            System.out.println(key + " : " + registers.get(key));
        }
    }

    public void print(){
        System.out.println("Process " + pid + " Type: " + type);
    }

}

