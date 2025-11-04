public class KernelProcess extends Process {
    public KernelProcess(int pid, int ppid){
        super(pid,ppid,ProcessType.kernel);
    }
    public void execute() {
        System.out.printf("Process id: %d%n", this.getPid());
        System.out.println("Kernel process handling system operations");
    }
}
