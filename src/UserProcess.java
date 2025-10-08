public class UserProcess extends Process{
    public UserProcess(int pid, int ppid){
        super(pid,ppid,"user");
    }
    public void execute() {
        System.out.println("User process running in user mode");
    }
}
