public class UserProcess extends Process{
    public UserProcess(int pid, int ppid){
        super(pid,ppid,ProcessType.user);
    }
    public void execute() {
        System.out.printf("Process id: %d%n", this.getPid());
        System.out.println("User process running in user mode");
    }
}
