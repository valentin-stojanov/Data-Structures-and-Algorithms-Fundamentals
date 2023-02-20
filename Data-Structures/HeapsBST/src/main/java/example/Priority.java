package example;

public enum Priority {
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private final int priorityCode;

    Priority(int priorityCode) {
        this.priorityCode = priorityCode;
    }

    public int getPriorityCode() {
        return priorityCode;
    }

}
