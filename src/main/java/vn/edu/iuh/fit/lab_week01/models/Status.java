package vn.edu.iuh.fit.lab_week01.models;

public enum Status {
    ACTIVE(1), DEACTIVE(0),DELETE(-1);

    private final int code;

    private Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status fromCode(int code) throws IllegalAccessException {
        for (Status status: Status.values()) {
            if (status.getCode() == code){
                return status;
            }
        }
        throw new IllegalAccessException("Invalid AccountStatus code: "+ code);
    }
}
