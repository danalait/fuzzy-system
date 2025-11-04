Adapter → Syscalls → kernel services → domain objects

Syscalls translate user actions (fork, exec, wait, etc.) into service calls.

Kernel services coordinate and modify domain objects but never expose them to adapters directly.

Domain holds the state (e.g., PID, registers, state, resources) and exposes no logic other than getters/setters or small helpers.
