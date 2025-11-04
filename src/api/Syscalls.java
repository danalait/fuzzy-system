package api;

public interface Syscalls {
    int fork();                                      // return child's pid or <0 on error
    int exec(String imagePath, String[] argv);       // replace image, return 0 or <0
    void exit(int status);                           // never returns
    int waitpid(int pid, int[] status, int options); // returns waited pid or <0
    int kill(int pid, int signal);                   // 0 on success or <0
    int getpid();                                    // caller's pid
}
