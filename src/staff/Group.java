package staff;//

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Group {
    public static List<Group> groups = new ArrayList<>();
    private String groupName;
    private int capacity; // количество мест в группе
    private int registeredAmount; // число занятых мест в группе
    private FacultyCategory facultyCategory;
    private short minAge;
    private int priceOfSingleActivity;

    private List<LocalDate> activityDates; // даты, в которые в группе проходят занятия на ближайший месяц


    public Group(String groupName, int capacity, FacultyCategory facultyCategory, int priceOfSingleActivity, short minAge) {
        this.groupName = groupName;
        this.capacity = capacity;
        this.facultyCategory = facultyCategory;
        registeredAmount = 0;

        this.priceOfSingleActivity = priceOfSingleActivity;
        this.minAge = minAge;

        activityDates = new ArrayList<>();

        activityDates.add(LocalDate.parse("2024-01-16"));
        activityDates.add(LocalDate.parse("2024-01-18"));
        activityDates.add(LocalDate.parse("2024-01-20"));
        activityDates.add(LocalDate.parse("2024-01-23"));
        activityDates.add(LocalDate.parse("2024-01-25"));
        activityDates.add(LocalDate.parse("2024-01-27"));


    }

    public static void populateGroupList() {
        groups.add(new Group("Шахматы 12", 12, FacultyCategory.CHESS, 200, (short) 8));
        groups.add(new Group("Шахматы 21", 12, FacultyCategory.CHESS, 200, (short) 8));
        groups.add(new Group("Шахматы +", 6, FacultyCategory.CHESS, 400, (short) 12));
        groups.add(new Group("Рисование 1253", 24, FacultyCategory.PAINTING, 350, (short) 8));
        groups.add(new Group("Рисование 3535", 24, FacultyCategory.PAINTING, 350, (short) 8));
        groups.add(new Group("Рисование +", 12, FacultyCategory.PAINTING, 700, (short) 14));
        groups.add(new Group("Дебаты 2343", 8, FacultyCategory.DEBUTING_CLUB, 150, (short) 14));
        groups.add(new Group("Дебаты +", 16, FacultyCategory.DEBUTING_CLUB, 300, (short) 16));
    }

    public static List<Group> selectGroupsByCategory(FacultyCategory selectedCategory) {
        return groups.stream().filter(group ->
                group.getFacultyCategory().equals(selectedCategory)).toList();
    }

    public void setActivityDates(List<LocalDate> newActivityDates) {
        activityDates = new ArrayList<>();
        activityDates.addAll(newActivityDates);
    }

    public boolean registerNewChild() {
        if(registeredAmount++ > capacity) {
            return false;
        }

        registeredAmount++;
        return true;
    }



    public String getGroupName() {
        return groupName;
    }

    public short getMinAge() {
        return minAge;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRegisteredAmount() {
        return registeredAmount;
    }

    public FacultyCategory getFacultyCategory() {
        return facultyCategory;
    }

    public int getPriceOfSingleActivity() {
        return priceOfSingleActivity;
    }

    public List<LocalDate> getActivityDates() {
        return activityDates;
    }

    @Override
    public String toString() {
        return groupName + " (" + capacity + " мест макс.)";
    }
}
