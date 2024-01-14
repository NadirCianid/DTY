package staff;

import java.time.LocalDate;

public class Registration {
    private LocalDate activityDate;
    private Group group;

    public Registration(LocalDate activityDate, Group group) {
        this.activityDate = activityDate;
        this.group = group;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Запись на " + activityDate.toString() +
                ", в группу " + group + ".";
    }
}
